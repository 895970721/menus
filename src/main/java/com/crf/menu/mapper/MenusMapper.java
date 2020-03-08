package com.crf.menu.mapper;

import com.crf.menu.entity.Menus;

import java.util.List;

public interface MenusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Integer id);

    List<Menus> selectByCategorySmallId(Integer id);

    List<Menus> selectByMenuName(String name);

    List<Menus> selectAll();

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
}