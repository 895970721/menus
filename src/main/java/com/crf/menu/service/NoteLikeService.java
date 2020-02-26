package com.crf.menu.service;

public interface NoteLikeService {
    void addLike(String token,Integer noteId);

    void delLike(String token,Integer noteId);
}
