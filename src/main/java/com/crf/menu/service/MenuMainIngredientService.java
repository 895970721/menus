package com.crf.menu.service;

import com.crf.menu.entity.MenuMainIngredient;

import java.util.List;

public interface MenuMainIngredientService {

    List<MenuMainIngredient> getMainIngredientByMenuId(Integer menuId);
}
