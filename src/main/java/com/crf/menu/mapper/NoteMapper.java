package com.crf.menu.mapper;

import com.crf.menu.entity.Note;

import java.util.List;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);

    List<Note> selectOrderByTime();
}