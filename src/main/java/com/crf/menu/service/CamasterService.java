package com.crf.menu.service;

import com.crf.menu.entity.Camaster;
import com.crf.menu.vo.CategoryVO;

import java.util.List;

public interface CamasterService {
    Camaster getDetail(Integer id);

    List<Camaster> getAllCamaster();

    List<CategoryVO> getAllCategory();
}
