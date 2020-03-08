package com.crf.menu.service.Impl;

import com.crf.menu.entity.*;
import com.crf.menu.enums.StatusCode;
import com.crf.menu.exception.NoteException;
import com.crf.menu.mapper.NoteMapper;
import com.crf.menu.service.NoteService;
import com.crf.menu.utils.DateUtil;
import com.crf.menu.utils.FileUtil;
import com.crf.menu.utils.UserTokenUtilImpl;
import com.crf.menu.vo.NoteListVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private NoteLikeServiceImpl noteLikeService;


    @Autowired
    private UserServiceImpl userService;

    @Value("${user.noteImagePath}")
    String userNoteImagePath;

    @Override
    public Integer addNote(String token, String noteName, String noteContent, MultipartFile file) {
        // 1.将上传的图片传到对应位置
        String imagePath = null;
        if(file != null){
            imagePath = FileUtil.MupFileMoveTo(file,userNoteImagePath);
        }
        // 2.数据入库
        Date date = new Date();
        Note note = new Note();
        note.setUserId(tokenUtil.getUser(token).getId());
        note.setNoteName(noteName);
        note.setNoteContent(noteContent);
        note.setLikeNum(0);
        note.setNoteImg(imagePath);
        note.setCreateTime(date);
        Integer insCnt = noteMapper.insertSelective(note);
        return insCnt;
    }

    @Override
    public List<NoteListVO> getNoteListVO(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Note> noteList = noteMapper.selectOrderByTime();
        List<NoteListVO> noteListVOList = getNoteListVOByList(noteList);
        return noteListVOList;
    }

    @Override
    public List<NoteListVO> getNoteListVOByList(List<Note> noteList) {
        List<NoteListVO> noteListVOList = new ArrayList<NoteListVO>();
        long now1 = System.currentTimeMillis();
        for(Note note : noteList){
            long now2 = note.getCreateTime().getTime();
            User user = userService.selectByUserId(note.getUserId());
            NoteListVO noteListVO = new NoteListVO();
            noteListVO.setCreateTime(DateUtil.getDateDiff(now2,now1));
            noteListVO.setLikeNum(note.getLikeNum());
            noteListVO.setNickName(user.getNickName());
            noteListVO.setNoteContent(note.getNoteContent());
            noteListVO.setNoteId(note.getId());
            noteListVO.setNoteImg(note.getNoteImg());
            noteListVO.setNoteName(note.getNoteName());
            noteListVO.setPhotoAddress(user.getPhotoAddress());
            noteListVO.setUserId(user.getId());
            noteListVOList.add(noteListVO);
        }
        return noteListVOList;
    }

    @Override
    public Integer addLikeNum(Integer noteId){
        Integer updCnt = noteMapper.addLikeNum(noteId);
        return updCnt;
    }

    @Override
    public Integer delLikeNum(Integer noteId){
        Note note = noteMapper.selectByPrimaryKey(noteId);
        if(note.getLikeNum() < 0){
            throw new NoteException(StatusCode.NoteLikeNumNoNegative);
        }
        Integer updCnt = noteMapper.delLikeNum(noteId);
        return updCnt;
    }

    @Override
    public List<NoteListVO> getNotesVOByNoteName(String note_name,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Note> noteList = noteMapper.selectByNoteName(note_name);
        return getNoteListVOByList(noteList);
    }

    @Override
    public List<NoteListVO> getNoteListVOByUserId(String token,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        User user = tokenUtil.getUser(token);
        List<NoteLike> noteLikeList = noteLikeService.selectByUserId(user.getId());
        List<Note> noteList = new ArrayList<>();
        for(NoteLike noteLike:noteLikeList)
        {
            Integer note_id = noteLike.getNoteId();
            Note note = noteMapper.selectByPrimaryKey(note_id);
            noteList.add(note);
        }
        List<NoteListVO> noteListVOList = getNoteListVOByList(noteList);
        return noteListVOList;
    }

}
