package com.crf.menu.service.Impl;

import com.crf.menu.vo.NoteListVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class NoteServiceImplTest {

    @Autowired
    private NoteServiceImpl noteService;


    @Test
    void test()
    {
        List<NoteListVO> noteListVOList = noteService.getNoteListVO(1,5);
        System.out.println(noteListVOList);
    }


}