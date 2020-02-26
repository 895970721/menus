package com.crf.menu.mapper;

import com.crf.menu.entity.NoteLike;

public interface NoteLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoteLike record);

    int insertSelective(NoteLike record);

    NoteLike selectByPrimaryKey(Integer id);

    NoteLike selectByNoteIdAndUserId(Integer userId,Integer noteId);

    int updateByPrimaryKeySelective(NoteLike record);

    int updateByPrimaryKey(NoteLike record);
}