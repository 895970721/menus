package com.crf.menu.controller;

import com.crf.menu.dto.UserDTO;
import com.crf.menu.entity.User;
import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.UserServiceImpl;
import com.crf.menu.utils.CheckToken;
import com.crf.menu.utils.UserTokenUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.util.HashMap;

@Validated
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @PostMapping(value = "login")
    public BaseResponse login(@Pattern(regexp = "^[\\w]{5,9}$",message = "用户名格式错误") @RequestParam("username") String username,
                              @Pattern(regexp = "^[a-zA-Z]\\d{6,9}$",message = "密码格式错误") @RequestParam("password") String password)
    {
        String token = userService.login(username,password);
        HashMap map  = new HashMap();
        map.put("token",token);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setData(map);
        return response;
    }

    @PostMapping(value = "register")
    public BaseResponse register(@Pattern(regexp = "^[\\w]{5,9}$",message = "用户名格式错误") @RequestParam("username") String username,
                                 @Pattern(regexp = "^[a-zA-Z]\\d{6,9}$",message = "密码格式错误") @RequestParam("password") String password)
    {
        Integer insCnt = userService.register(username,password);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        HashMap map  = new HashMap();
        map.put("insCnt",insCnt);
        response.setData(map);
        return response;
    }

    @CheckToken
    @PostMapping(value = "updNickName")
    public BaseResponse updNickName(@Pattern(regexp = "^[\\s\\S]{2,15}$",message = "昵称长度错误") @RequestParam("nickname") String nickname,
                                    @RequestParam("token") String token)
    {
        Integer updCnt = userService.updNickName(token,nickname);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setMsg("修改昵称成功");
        HashMap map  = new HashMap();
        map.put("updCnt",updCnt);
        response.setData(map);
        return response;
    }

    @CheckToken
    @PostMapping("uploadimage")
    public BaseResponse uploadimg(@RequestParam MultipartFile file, @RequestParam("token") String token)
    {
        User user =  tokenUtil.getUser(token);
        HashMap map = userService.uploadImage(user,file);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setMsg("上传头像成功");
        response.setData(map);
        return response;
    }

    @CheckToken
    @PostMapping("getInfo")
    public BaseResponse getInfo(@RequestParam("token") String token)
    {
        UserDTO userDTO = userService.getInfo(token);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        response.setMsg("获取用户信息成功");
        response.setData(userDTO);
        return response;
    }
}
