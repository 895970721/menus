package com.crf.menu.service;

import com.crf.menu.entity.MenuAccessories;

import java.util.List;

public interface MenuAccessoriesService {

    List<MenuAccessories> getMenuAccessoriesByMenuId(Integer menuId);
}
