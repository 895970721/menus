package com.crf.menu.service.Impl;

import com.crf.menu.entity.MenuAccessories;
import com.crf.menu.mapper.MenuAccessoriesMapper;
import com.crf.menu.service.MenuAccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuAccessoriesServiceImpl implements MenuAccessoriesService {

    @Autowired
    private MenuAccessoriesMapper menuAccessoriesMapper;

    public List<MenuAccessories> getMenuAccessoriesByMenuId(Integer menuId)
    {
        return menuAccessoriesMapper.selectByMenuId(menuId);
    }
}
