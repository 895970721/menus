package com.crf.menu.service.Impl;

import com.crf.menu.entity.NoteLike;
import com.crf.menu.entity.User;
import com.crf.menu.enums.StatusCode;
import com.crf.menu.exception.NoteLikeException;
import com.crf.menu.mapper.NoteLikeMapper;
import com.crf.menu.service.NoteLikeService;
import com.crf.menu.utils.UserTokenUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteLikeServiceImpl implements NoteLikeService {

    @Autowired
    private NoteLikeMapper noteLikeMapper;

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @Autowired
    private NoteServiceImpl noteService;

    @Override
    @Transactional
    public void addLike(String token, Integer noteId) {
        User user = tokenUtil.getUser(token);
        NoteLike noteLike = noteLikeMapper.selectByNoteIdAndUserId(user.getId(),noteId);
        if (noteLike != null){
            throw new NoteLikeException(StatusCode.NoteLikeExit);
        }
        noteLike = new NoteLike();
        noteLike.setNoteId(noteId);
        noteLike.setUserId(user.getId());
        noteLikeMapper.insertSelective(noteLike);
        noteService.addLikeNum(noteId);
    }

    @Override
    @Transactional
    public void delLike(String token, Integer noteId) {
        User user = tokenUtil.getUser(token);
        NoteLike noteLike = noteLikeMapper.selectByNoteIdAndUserId(user.getId(),noteId);
        if (noteLike == null){
            throw new NoteLikeException(StatusCode.NoteLikeNoExit);
        }
        noteLikeMapper.deleteByPrimaryKey(noteLike.getId());
        noteService.delLikeNum(noteId);
    }

    @Override
    public List<NoteLike> selectByUserId(Integer userId) {
        return noteLikeMapper.selectByUserId(userId);
    }
}
