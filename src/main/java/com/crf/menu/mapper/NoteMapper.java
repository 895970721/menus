package com.crf.menu.mapper;

import com.crf.menu.entity.Note;
import com.crf.menu.vo.NoteListVO;

import java.util.List;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer id);

    List<Note> selectByNoteName(String noteName);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);

    List<Note> selectOrderByTime();

    Integer addLikeNum(Integer noteId);

    Integer delLikeNum(Integer noteId);

    List<Note> getNoteListByUserId(Integer userId);
}