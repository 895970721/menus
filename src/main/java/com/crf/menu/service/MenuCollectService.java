package com.crf.menu.service;

import com.crf.menu.entity.MenuCollect;

import java.util.List;

public interface MenuCollectService {
    void addCollect(String token,Integer menu_id);

    void delCollect(String token,Integer menu_id);

    List<MenuCollect> selectByUserId(Integer user_id);
}
