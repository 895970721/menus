package com.crf.menu.mapper;

import com.crf.menu.entity.MenuMainIngredient;

import java.util.List;

public interface MenuMainIngredientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuMainIngredient record);

    int insertSelective(MenuMainIngredient record);

    MenuMainIngredient selectByPrimaryKey(Integer id);

    List<MenuMainIngredient> selectByMenuId(Integer id);

    int updateByPrimaryKeySelective(MenuMainIngredient record);

    int updateByPrimaryKey(MenuMainIngredient record);
}