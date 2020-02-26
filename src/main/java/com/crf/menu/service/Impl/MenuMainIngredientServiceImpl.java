package com.crf.menu.service.Impl;

import com.crf.menu.entity.MenuMainIngredient;
import com.crf.menu.mapper.MenuMainIngredientMapper;
import com.crf.menu.service.MenuMainIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuMainIngredientServiceImpl implements MenuMainIngredientService {

    @Autowired
    private MenuMainIngredientMapper menuMainIngredientMapper;

    public List<MenuMainIngredient> getMainIngredientByMenuId(Integer menuId)
    {
        return menuMainIngredientMapper.selectByMenuId(menuId);
    }
}
