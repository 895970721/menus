package com.crf.menu.service;

import com.crf.menu.vo.NoteListVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoteService {
    Integer addNote(String token,String noteName,String noteContent, MultipartFile file);

    List<NoteListVO> getNoteListVO(Integer pageNum,Integer pageSize);
}
