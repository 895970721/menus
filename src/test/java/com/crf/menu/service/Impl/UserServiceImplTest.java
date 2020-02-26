package com.crf.menu.service.Impl;

import com.crf.menu.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void selectByUserName() {
        String username = "895970721";
        User user = userService.selectByUserName(username);
        System.out.print(user);
    }

    @Test
    void login() {
        String username = "895970721";
        String password = "a365432019";
        String token = userService.login(username,password);
        System.out.print(token);
    }
}