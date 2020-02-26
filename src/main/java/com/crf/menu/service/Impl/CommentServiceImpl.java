package com.crf.menu.service.Impl;

import com.crf.menu.entity.Comment;
import com.crf.menu.entity.User;
import com.crf.menu.mapper.CommentMapper;
import com.crf.menu.service.CommentService;
import com.crf.menu.utils.UserTokenUtilImpl;
import com.crf.menu.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @Override
    public List<CommentVO> selectByNoteId(Integer noteId) {
        List<Comment> commentList = commentMapper.selectByNoteId(noteId);
        List<CommentVO> commentVOList = new ArrayList<CommentVO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Comment comment : commentList){
            User fromUser = userService.selectByUserId(comment.getFromUid());
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment,commentVO);
            commentVO.setCreateTime(sdf.format(comment.getCreateTime()));
            commentVO.setFromNickName(fromUser.getNickName());
            commentVO.setFromUserImage(fromUser.getPhotoAddress());
            if(comment.getToUid() != null){
                User toUser = userService.selectByUserId(comment.getToUid());
                commentVO.setToNickName(toUser.getNickName());
            }
            commentVOList.add(commentVO);
        }
        return commentVOList;
    }

    @Override
    public Integer addComment(String token, Integer noteId, String content ,Integer toUid) {
        User user = tokenUtil.getUser(token);
        Date date = new Date();
        Comment comment = new Comment();
        comment.setCreateTime(date);
        comment.setContent(content);
        comment.setFromUid(user.getId());
        comment.setNoteId(noteId);
        if(toUid != null){
            comment.setToUid(toUid);
        }
        Integer insCnt = commentMapper.insertSelective(comment);
        return insCnt;
    }

    @Override
    public Integer delComment(Integer commentId) {
        Integer delCnt = commentMapper.deleteByPrimaryKey(commentId);
        return delCnt;
    }
}
