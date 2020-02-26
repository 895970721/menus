package com.crf.menu.controller;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.CommentServiceImpl;
import com.crf.menu.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping(value = "selectByNoteId")
    public BaseResponse selectByNoteId(@RequestParam("noteId") Integer noteId){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        List<CommentVO> commentList = commentService.selectByNoteId(noteId);
        response.setData(commentList);
        return response;
    }

}
