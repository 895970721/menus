package com.crf.menu.mapper;

import com.crf.menu.entity.MenuSteps;

import java.util.List;

public interface MenuStepsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuSteps record);

    int insertSelective(MenuSteps record);

    MenuSteps selectByPrimaryKey(Integer id);
    
    List<MenuSteps> selectByMenuId(Integer menuId);

    int updateByPrimaryKeySelective(MenuSteps record);

    int updateByPrimaryKey(MenuSteps record);
}