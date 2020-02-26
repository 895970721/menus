package com.crf.menu.controller;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.MenusServiceImpl;
import com.crf.menu.utils.CheckToken;
import com.crf.menu.vo.MenuDetailVO;
import com.crf.menu.vo.MenuListVO;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RestController
@RequestMapping("/menus")
public class MenusController {

    @Autowired
    private MenusServiceImpl menusService;

    @PostMapping(value = "/getMenuById")
    public BaseResponse getMenuById(@Min (value = 1,message = "id非法") @RequestParam("id") Integer id)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        MenuDetailVO menuDetailVO = menusService.getDetail(id);
        response.setData(menuDetailVO);
        return response;
    }

    @PostMapping(value = "/getMenusVOByCategorySmallId")
    public BaseResponse getMenusVOByCategorySmallId(@Min (value = 1,message = "id非法") @RequestParam("id") Integer id,
                                                    @Min (value = 1,message = "pageNum非法") @RequestParam("pageNum") Integer pageNum,
                                                    @Min (value = 1,message = "pageSize非法") @RequestParam("pageSize") Integer pageSize)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<MenuListVO> menuListVOList = menusService.getMenusVOByCategorySmallId(id,pageNum,pageSize);
        response.setData(menuListVOList);
        return response;
    }

    @PostMapping(value = "/getMenusVOByCategorySmallName")
    public BaseResponse getMenusVOByCategorySmallName(@Pattern(regexp = "^\\w{2,20}$",message = "name非法") @RequestParam("name") String name,
                                                    @Min (value = 1,message = "pageNum非法") @RequestParam("pageNum") Integer pageNum,
                                                    @Min (value = 1,message = "pageSize非法") @RequestParam("pageSize") Integer pageSize)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<MenuListVO> menuListVOList = menusService.getMenusVOByCategorySmallName(name,pageNum,pageSize);
        response.setData(menuListVOList);
        return response;
    }

    @CheckToken
    @PostMapping(value = "/getMenusVOByUserId")
    public BaseResponse getMenusVOByUserId(@RequestParam("token") String token)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<MenuListVO> menuListVOList = menusService.getMenusVOByUserId(token);
        response.setData(menuListVOList);
        return response;
    }

    @PostMapping(value = "/getMenusVOByMenuName")
    public BaseResponse getMenusVOByMenuName(@RequestParam("menuName") String menuName,
                                             @Min (value = 1,message = "pageNum非法") @RequestParam("pageNum") Integer pageNum,
                                             @Min (value = 1,message = "pageSize非法") @RequestParam("pageSize") Integer pageSize)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<MenuListVO> menuListVOList = menusService.getMenusVOByMenuName(menuName,pageNum,pageSize);
        response.setData(menuListVOList);
        return response;
    }
}
