package com.crf.menu.service.Impl;

import com.crf.menu.entity.Menus;
import com.crf.menu.vo.MenuDetailVO;
import com.crf.menu.vo.MenuListVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class MenusServiceImplTest {

    @Autowired
    private MenusServiceImpl menusService;

    @Test
    void getDetail() {
        Integer menuId = 2;
        MenuDetailVO menuDetailVO = menusService.getDetail(menuId);
        System.out.print(menuDetailVO);
    }

    @Test
    void getMenusByCategorySmallId(){
        Integer categorySmall = 2;
        Integer pageSize = 5;
        Integer pageNum  = 2;
        List<Menus> menusList = menusService.getMenusByCategorySmallId(categorySmall,pageNum,pageSize);

        Iterator<Menus> iter = menusList.iterator();
        while(iter.hasNext()){
            Menus menus = iter.next();
            System.out.println(menus);
        }
    }

    @Test
    void getMenusVOByCategorySmallId(){
        Integer categorySmall = 1;
        Integer pageSize = 5;
        Integer pageNum  = 1;
        List<MenuListVO> menuListVOList = menusService.getMenusVOByCategorySmallId(categorySmall,pageNum,pageSize);
        for (MenuListVO menuListVO:menuListVOList)
        {
            System.out.println(menuListVO);
        }
    }

    @Test
    void getMenusVOByMenuName(){
        String name = "肉";
        Integer pageSize = 5;
        Integer pageNum  = 1;

        List<MenuListVO> menuListVOList = menusService.getMenusVOByMenuName(name,pageNum,pageSize);
        for (MenuListVO menuListVO:menuListVOList)
        {
            System.out.println(menuListVO);
        }
    }
}