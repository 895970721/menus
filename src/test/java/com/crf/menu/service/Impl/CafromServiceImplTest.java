package com.crf.menu.service.Impl;

import com.crf.menu.entity.Cafrom;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CafromServiceImplTest {

    @Autowired
    private CafromServiceImpl cafromService;

    @Test
    void getDetailByCategorySmallName() {
        String name = "zaocan";
        Cafrom cafrom = cafromService.getDetailByCategorySmallName(name);
        System.out.println(cafrom);
    }
}