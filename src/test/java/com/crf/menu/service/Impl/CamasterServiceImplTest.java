package com.crf.menu.service.Impl;

import com.crf.menu.vo.CategoryVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CamasterServiceImplTest {

    @Autowired
    private CamasterServiceImpl camasterService;

    @Test
    void getAllCategory() {
        List<CategoryVO> categoryVOListList = camasterService.getAllCategory();
        System.out.print(categoryVOListList);
    }
}