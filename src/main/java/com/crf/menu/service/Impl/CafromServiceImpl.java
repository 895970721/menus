package com.crf.menu.service.Impl;

import com.crf.menu.entity.Cafrom;
import com.crf.menu.mapper.CafromMapper;
import com.crf.menu.service.CafromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafromServiceImpl implements CafromService {

    @Autowired
    private CafromMapper cafromMapper;

    @Override
    public Cafrom getDetail(Integer id) {
        return cafromMapper.selectByPrimaryKey(id);
    }

    @Override
    public Cafrom getDetailByCategorySmallName(String name) {
        return cafromMapper.getCafromByCategorySmallName(name);
    }

    public List<Cafrom> getAllCafrom()
    {
        return cafromMapper.getAllCafrom();
    }

    @Override
    public List<Cafrom> getCafromListByCateBig(Integer categoryBigId) {
        return cafromMapper.getCafromListByCateBigId(categoryBigId);
    }
}
