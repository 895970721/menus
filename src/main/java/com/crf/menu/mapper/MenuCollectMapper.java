package com.crf.menu.mapper;

import com.crf.menu.entity.MenuCollect;

import java.util.List;

public interface MenuCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuCollect record);

    int insertSelective(MenuCollect record);

    MenuCollect selectByPrimaryKey(Integer id);

    MenuCollect selectByUserIdAndMenuId(Integer menu_id,Integer user_id);

    List<MenuCollect> selectByUserId(Integer user_id);

    int updateByPrimaryKeySelective(MenuCollect record);

    int updateByPrimaryKey(MenuCollect record);
}