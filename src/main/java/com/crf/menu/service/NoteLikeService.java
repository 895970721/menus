package com.crf.menu.service;

import com.crf.menu.entity.NoteLike;

import java.util.List;

public interface NoteLikeService {
    void addLike(String token,Integer noteId);

    void delLike(String token,Integer noteId);

    List<NoteLike> selectByUserId(Integer userId);
}
