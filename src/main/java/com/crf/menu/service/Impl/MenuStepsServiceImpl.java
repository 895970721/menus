package com.crf.menu.service.Impl;

import com.crf.menu.entity.MenuSteps;
import com.crf.menu.mapper.MenuStepsMapper;
import com.crf.menu.service.MenuStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuStepsServiceImpl implements MenuStepsService {

    @Autowired
    private MenuStepsMapper menuStepsMapper;

    @Override
    public List<MenuSteps> getMenuStepsByMenuId(Integer menuId) {
        return menuStepsMapper.selectByMenuId(menuId);
    }
}
