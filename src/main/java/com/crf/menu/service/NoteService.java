package com.crf.menu.service;

import com.crf.menu.entity.Note;
import com.crf.menu.vo.NoteListVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoteService {
    Integer addNote(String token,String noteName,String noteContent, MultipartFile file);

    List<NoteListVO> getNoteListVO(Integer pageNum,Integer pageSize);

    List<NoteListVO> getNoteListVOByList(List<Note> noteList, Integer pageNum, Integer pageSize);

    Integer addLikeNum(Integer noteId);

    Integer delLikeNum(Integer noteId);

    List<NoteListVO> getNotesVOByNoteName(String note_name,Integer pageNum,Integer pageSize);

    List<NoteListVO> getNoteListVOByUserId(String token,Integer pageNum,Integer pageSize);
}
