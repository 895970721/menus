package com.crf.menu.controller;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.MenuCollectServiceImpl;
import com.crf.menu.utils.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(value = "menuCollect")
public class MenuCollectController {

    @Autowired
    private MenuCollectServiceImpl menuCollectService;

    /**
     * 增加收藏
     * @param token    用户标识
     * @param menu_id  菜谱id
     * @return
     */
    @CheckToken
    @PostMapping(value = "addCollect")
    public BaseResponse addCollect(@RequestParam("token") String token,
                                   @Min(value = 1,message = "menu_id非法") @RequestParam("menu_id") Integer menu_id)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        menuCollectService.addCollect(token,menu_id);
        response.setMsg("添加收藏成功");
        return response;
    }

    /**
     * 删除收藏
     * @param token    用户标识
     * @param menu_id  菜谱id
     * @return
     */
    @CheckToken
    @PostMapping(value = "delCollect")
    public BaseResponse delCollect(@RequestParam("token") String token,
                                   @Min(value = 1,message = "menu_id非法") @RequestParam("menu_id") Integer menu_id)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        menuCollectService.delCollect(token,menu_id);
        response.setMsg("删除收藏成功");
        return response;
    }

}
