package com.crf.menu.mapper;

import com.crf.menu.entity.Camaster;

import java.util.List;

public interface CamasterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Camaster record);

    int insertSelective(Camaster record);

    Camaster selectByPrimaryKey(Integer id);

    List<Camaster> getAllCamaster();

    int updateByPrimaryKeySelective(Camaster record);

    int updateByPrimaryKey(Camaster record);
}