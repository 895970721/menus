package com.crf.menu.mapper;

import com.crf.menu.entity.MenuAccessories;

import java.util.List;

public interface MenuAccessoriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuAccessories record);

    int insertSelective(MenuAccessories record);

    MenuAccessories selectByPrimaryKey(Integer id);

    List<MenuAccessories> selectByMenuId(Integer menu_id);

    int updateByPrimaryKeySelective(MenuAccessories record);

    int updateByPrimaryKey(MenuAccessories record);
}