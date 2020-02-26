package com.crf.menu.controller;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.CamasterServiceImpl;
import com.crf.menu.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "camaster")
public class CamasterController {

    @Autowired
    private CamasterServiceImpl camasterService;

    @PostMapping(value = "getAllCategory")
    public BaseResponse getAllCategory()
    {
        List<CategoryVO> categoryVOList = camasterService.getAllCategory();
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setMsg("取得全部分类成功");
        response.setData(categoryVOList);
        return response;
    }
}
