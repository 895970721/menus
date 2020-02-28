package com.crf.menu.mapper;

import com.crf.menu.entity.NoteLike;

import java.util.List;

public interface NoteLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoteLike record);

    int insertSelective(NoteLike record);

    NoteLike selectByPrimaryKey(Integer id);

    NoteLike selectByNoteIdAndUserId(Integer userId,Integer noteId);

    List<NoteLike> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(NoteLike record);

    int updateByPrimaryKey(NoteLike record);
}