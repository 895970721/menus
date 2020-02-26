package com.crf.menu.service.Impl;

import com.crf.menu.entity.*;
import com.crf.menu.enums.StatusCode;
import com.crf.menu.exception.CafromException;
import com.crf.menu.exception.MenuException;
import com.crf.menu.mapper.*;
import com.crf.menu.service.MenusService;
import com.crf.menu.utils.RedisUtil;
import com.crf.menu.utils.UserTokenUtilImpl;
import com.crf.menu.vo.MenuDetailVO;
import com.crf.menu.vo.MenuListVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenusMapper menusMapper;

    @Autowired
    private CafromServiceImpl cafromService;

    @Autowired
    private CamasterServiceImpl camasterService;

    @Autowired
    private MenuMainIngredientServiceImpl menuMainIngredientService;

    @Autowired
    private MenuAccessoriesServiceImpl menuAccessoriesService;

    @Autowired
    private MenuStepsServiceImpl menuStepsService;

    @Autowired
    private MenuCollectServiceImpl menuCollectService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @Value("${Image.menu}")
    String menuImagePath;

    @Override
    public MenuDetailVO getDetail(Integer id) {
        Menus menus = menusMapper.selectByPrimaryKey(id);
        if (menus == null)
        {
            throw new MenuException(StatusCode.MenuNoExit);
        }

        MenuDetailVO menuDetailVO = new MenuDetailVO();
        if (redisUtil.get("menu:menudetail:"+id) != null)
        {
            menuDetailVO = (MenuDetailVO) redisUtil.get("menu:menudetail:"+id);
            redisUtil.expire("menu:menudetail:"+id,86400);
        }
        else
        {
            /**
             * 1. 数据准备
             */
            // 获取小类
            Cafrom cafrom = cafromService.getDetail(menus.getCafromId());
            String category_small = cafrom.getCategorySmall();
            // 获取大类
            Camaster camaster = camasterService.getDetail(cafrom.getCategoryBigId());
            String category_big = camaster.getCategoryBig();
            // 获取主料
            List<MenuMainIngredient> menuMainIngredientList = menuMainIngredientService.getMainIngredientByMenuId(menus.getId());
            // 获取配料
            List<MenuAccessories> menuAccessoriesList = menuAccessoriesService.getMenuAccessoriesByMenuId(menus.getId());
            // 获取步骤
            List<MenuSteps> menuStepsList = menuStepsService.getMenuStepsByMenuId(menus.getId());

            /**
             * 2. 数据组合
             */
            menuDetailVO.setId(menus.getId());
            menuDetailVO.setName(menus.getName());
            menuDetailVO.setMenuImg(menuImagePath+category_big+"/"+category_small+"/detail_img/"+menus.getMenuImg());
            List<String> MainInName  = new ArrayList<>();
            List<String> MainInNum   = new ArrayList<>();
            List<String> AccesInName = new ArrayList<>();
            List<String> AccesInNum  = new ArrayList<>();
            List<Integer> MenuStpNum = new ArrayList<>();
            List<String> MenuStepImg = new ArrayList<>();
            List<String> MenuStpWord = new ArrayList<>();
            for (MenuMainIngredient mmi : menuMainIngredientList)
            {
                MainInName.add(mmi.getMainIngredient());
                MainInNum.add(mmi.getDosage());
            }
            for (MenuAccessories ma : menuAccessoriesList)
            {
                AccesInName.add(ma.getAccessories());
                AccesInNum.add(ma.getDosage());
            }
            for(MenuSteps ms : menuStepsList)
            {
                MenuStpNum.add(ms.getStepNum());
                MenuStepImg.add(menuImagePath+category_big+"/"+category_small+"/step_img/"+ms.getStepImg());
                MenuStpWord.add(ms.getStepWord());
            }
            menuDetailVO.setMainIngredient(MainInName);
            menuDetailVO.setDosage(MainInNum);
            menuDetailVO.setAccessories(AccesInName);
            menuDetailVO.setAcdosage(AccesInNum);
            menuDetailVO.setStepNum(MenuStpNum);
            menuDetailVO.setStepImg(MenuStepImg);
            menuDetailVO.setStepWord(MenuStpWord);
            redisUtil.set("menu:menudetail:"+id,menuDetailVO,86400);
        }
        return menuDetailVO;
    }

    @Override
    public List<MenuListVO> getMenusVOByCategorySmallId(Integer id,Integer pageNum,Integer pageSize) {
        List<Menus> menusList = getMenusByCategorySmallId(id,pageNum,pageSize);
        List<MenuListVO> menuListVOList = getMenusVOByMenuList(menusList);
        return menuListVOList;
    }

    @Override
    public List<MenuListVO> getMenusVOByCategorySmallName(String name,Integer pageNum,Integer pageSize) {
        Cafrom cafrom = cafromService.getDetailByCategorySmallName(name);
        if(cafrom == null)
        {
            throw new CafromException(StatusCode.CafromNoExit);
        }

        return getMenusVOByCategorySmallId(cafrom.getId(),pageNum,pageSize);
    }

    @Override
    public List<MenuListVO> getMenusVOByUserId(String token) {
        User user = tokenUtil.getUser(token);
        List<MenuCollect> menuCollectList = menuCollectService.selectByUserId(user.getId());
        List<Menus> menusList = new ArrayList<>();
        for(MenuCollect menuCollect:menuCollectList)
        {
            Integer menu_id = menuCollect.getMenuId();
            Menus menus = menusMapper.selectByPrimaryKey(menu_id);
            menusList.add(menus);
        }
        List<MenuListVO> menuListVOList = getMenusVOByMenuList(menusList);
        return menuListVOList;
    }

    @Override
    public List<Menus> getMenusByCategorySmallId(Integer id,Integer pageNum,Integer pageSize) {
        if(cafromService.getDetail(id) == null)
        {
            throw new CafromException(StatusCode.CafromNoExit);
        }
        PageHelper.startPage(pageNum,pageSize);
        return menusMapper.selectByCategorySmallId(id);
    }

    public List<MenuListVO> getMenusVOByMenuList(List<Menus> list)
    {
        List<MenuListVO> menuListVOList = new ArrayList<>();
        for(Menus menus:list)
        {
            // 获取小类
            Cafrom cafrom = cafromService.getDetail(menus.getCafromId());
            // 获取大类
            Camaster camaster = camasterService.getDetail(cafrom.getCategoryBigId());
            String category_small = cafrom.getCategorySmall();
            String category_big = camaster.getCategoryBig();
            String menuMaterial = "";
            List<MenuMainIngredient> menuMainIngredientList = menuMainIngredientService.getMainIngredientByMenuId(menus.getId());
            for(MenuMainIngredient menuMainIngredient: menuMainIngredientList)
            {
                menuMaterial += menuMainIngredient.getMainIngredient()+",";
            }
            List<MenuAccessories> menuAccessoriesList = menuAccessoriesService.getMenuAccessoriesByMenuId(menus.getId());
            for(MenuAccessories menuAccessories: menuAccessoriesList)
            {
                menuMaterial += menuAccessories.getAccessories()+",";
            }
            menuMaterial = menuMaterial.substring(0,menuMaterial.length()-1);
            MenuListVO menuListVO = new MenuListVO();
            menuListVO.setId(menus.getId());
            menuListVO.setName(menus.getName());
            menuListVO.setMenuImg(menuImagePath+category_big+"/"+category_small+"/detail_img/"+menus.getMenuImg());
            menuListVO.setMenuMaterial(menuMaterial);
            menuListVOList.add(menuListVO);
        }
        return menuListVOList;
    }

    public List<MenuListVO> getMenusVOByMenuName(String name,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Menus> menusList = menusMapper.selectByMenuName(name);
        List<MenuListVO> menuListVOList = getMenusVOByMenuList(menusList);
        return menuListVOList;
    }
}
