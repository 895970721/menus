package com.crf.menu.mapper;

import com.crf.menu.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}