package com.crf.menu.controller;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.service.Impl.CommentServiceImpl;
import com.crf.menu.utils.CheckToken;
import com.crf.menu.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @CheckToken
    @PostMapping(value = "add")
    public BaseResponse addComment(@RequestParam("token") String token,@RequestParam("noteId") Integer noteId,
                                   @RequestParam("content") String content,@RequestParam(value = "toUid",required = false) Integer toUid){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Integer insCnt = commentService.addComment(token,noteId,content,toUid);
        HashMap map = new HashMap();
        map.put("insCnt",insCnt);
        response.setData(map);
        return response;
    }

    @CheckToken
    @PostMapping(value = "del")
    public BaseResponse delComment(@RequestParam("commentId") Integer commentId){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Integer delCnt = commentService.delComment(commentId);
        HashMap map = new HashMap();
        map.put("delCnt",delCnt);
        response.setData(map);
        return response;
    }

}
