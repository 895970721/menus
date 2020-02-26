package com.crf.menu.service.Impl;

import com.crf.menu.dto.CategorySmallDTO;
import com.crf.menu.entity.Cafrom;
import com.crf.menu.entity.Camaster;
import com.crf.menu.mapper.CamasterMapper;
import com.crf.menu.service.CamasterService;
import com.crf.menu.utils.RedisUtil;
import com.crf.menu.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CamasterServiceImpl implements CamasterService {

    @Autowired
    private CamasterMapper camasterMapper;

    @Autowired
    private CafromServiceImpl cafromService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Camaster getDetail(Integer id) {
        return camasterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Camaster> getAllCamaster() {
        return camasterMapper.getAllCamaster();
    }

    @Override
    public List<CategoryVO> getAllCategory() {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        if (redisUtil.get("categoryVOList") != null)
        {
            categoryVOList = (List<CategoryVO>) redisUtil.get("categoryVOList");
        }
        else
        {
            List<Camaster> camasterList = camasterMapper.getAllCamaster();
            for(Camaster camaster: camasterList)
            {
                List<Cafrom> cafromList = cafromService.getCafromListByCateBig(camaster.getId());
                List<CategorySmallDTO> categorySmallDTOList = new ArrayList<>();
                for(Cafrom cafrom: cafromList)
                {
                    CategorySmallDTO categorySmallDTO = new CategorySmallDTO();
                    categorySmallDTO.setCategorySmallId(cafrom.getId());
                    categorySmallDTO.setCategorySmallWord(cafrom.getCategorySmall());
                    categorySmallDTOList.add(categorySmallDTO);
                }
                CategoryVO categoryVO = new CategoryVO();
                categoryVO.setCategorySmallDTOList(categorySmallDTOList);
                categoryVO.setCategoryBigId(camaster.getId());
                categoryVO.setCategoryBigWord(camaster.getCategoryBig());
                categoryVOList.add(categoryVO);
            }
            redisUtil.set("categoryVOList",categoryVOList);
        }
        return categoryVOList;
    }
}
