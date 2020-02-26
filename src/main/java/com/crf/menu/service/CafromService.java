package com.crf.menu.service;

import com.crf.menu.entity.Cafrom;

import java.util.List;

public interface CafromService {

    Cafrom getDetail(Integer id);

    Cafrom getDetailByCategorySmallName(String name);

    List<Cafrom> getAllCafrom();

    List<Cafrom> getCafromListByCateBig(Integer categoryBigId);
}
