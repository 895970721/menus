package com.crf.menu.service.Impl;

import com.crf.menu.vo.CommentVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    void selectByNoteId(){
        List<CommentVO> commentVOList = commentService.selectByNoteId(2);
        System.out.println(commentVOList);
    }

}