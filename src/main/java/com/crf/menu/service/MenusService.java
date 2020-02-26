package com.crf.menu.service;

import com.crf.menu.entity.Menus;
import com.crf.menu.vo.MenuDetailVO;
import com.crf.menu.vo.MenuListVO;

import java.util.List;

public interface MenusService {

    // 通过id,获取单个菜品信息
    MenuDetailVO getDetail(Integer id);

    // 通过小类id,查询对应菜品VO列表(分页)
    List<MenuListVO> getMenusVOByCategorySmallId(Integer id,Integer pageNum,Integer pageSize);

    // 通过小类名字,查询对应菜品VO列表(分页)
    List<MenuListVO> getMenusVOByCategorySmallName(String name,Integer pageNum,Integer pageSize);

    // 通过用户id,查询收藏的对应菜品VO列表(分页)
    List<MenuListVO> getMenusVOByUserId(String token);

    // 通过小类id,查询对应菜谱列表(分页)
    List<Menus> getMenusByCategorySmallId(Integer id,Integer pageNum,Integer pageSize);

    // 通过菜品列表,查询对应菜品VO
    List<MenuListVO> getMenusVOByMenuList(List<Menus> list);

    // 通过菜品名字,查询对应菜品VO
    List<MenuListVO> getMenusVOByMenuName(String name,Integer pageNum,Integer pageSize);
}
