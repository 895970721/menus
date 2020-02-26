package com.crf.menu.service;

import com.crf.menu.vo.CommentVO;

import java.util.List;

public interface CommentService {

    List<CommentVO> selectByNoteId(Integer noteId);

    Integer addComment(String token,Integer noteId,String Content,Integer toUid);

    Integer delComment(Integer commentId);

}
