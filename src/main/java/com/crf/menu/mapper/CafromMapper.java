package com.crf.menu.mapper;

import com.crf.menu.entity.Cafrom;

import java.util.List;

public interface CafromMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cafrom record);

    int insertSelective(Cafrom record);

    Cafrom selectByPrimaryKey(Integer id);

    Cafrom getCafromByCategorySmallName(String name);

    List<Cafrom> getAllCafrom();

    List<Cafrom> getCafromListByCateBigId(Integer categoryBigId);

    int updateByPrimaryKeySelective(Cafrom record);

    int updateByPrimaryKey(Cafrom record);
}