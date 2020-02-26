package com.crf.menu.controller;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.NoteLikeServiceImpl;
import com.crf.menu.service.NoteLikeService;
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
@RequestMapping(value = "noteLike")
public class NoteLikeController {

    @Autowired
    private NoteLikeServiceImpl noteLikeService;

    @CheckToken
    @PostMapping(value = "addLike")
    public BaseResponse addLike(@RequestParam("token") String token,
                                @Min(value = 1,message = "note_id非法") @RequestParam("note_id") Integer note_id){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        noteLikeService.addLike(token,note_id);
        response.setMsg("添加喜欢成功");
        return response;
    }

    @CheckToken
    @PostMapping(value = "delLike")
    public BaseResponse delLike(@RequestParam("token") String token,
                                @Min(value = 1,message = "note_id非法") @RequestParam("note_id") Integer note_id){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        noteLikeService.delLike(token,note_id);
        response.setMsg("删除喜欢成功");
        return response;
    }
}
