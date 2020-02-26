package com.crf.menu.service;

import com.crf.menu.entity.MenuSteps;

import java.util.List;

public interface MenuStepsService {
    List<MenuSteps> getMenuStepsByMenuId(Integer menuId);


}
