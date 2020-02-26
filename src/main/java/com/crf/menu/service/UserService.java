package com.crf.menu.service;

import com.crf.menu.dto.UserDTO;
import com.crf.menu.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface UserService {
    User selectByUserName(String username);

    String login(String username, String password);

    Integer register(String username,String password);

    Integer updNickName(String token,String nickname);

    HashMap uploadImage(User user, MultipartFile file);

    UserDTO getInfo(String token);

    User selectByUserId(Integer id);
}
