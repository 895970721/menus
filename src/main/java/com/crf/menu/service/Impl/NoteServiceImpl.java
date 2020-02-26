package com.crf.menu.service.Impl;

import com.crf.menu.entity.Note;
import com.crf.menu.entity.User;
import com.crf.menu.mapper.NoteMapper;
import com.crf.menu.service.NoteService;
import com.crf.menu.utils.FileUtil;
import com.crf.menu.utils.UserTokenUtilImpl;
import com.crf.menu.vo.NoteListVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
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
        List<NoteListVO> noteListVOList = new ArrayList<NoteListVO>();
        List<Note> noteList = noteMapper.selectOrderByTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Note note : noteList){
            User user = userService.selectByUserId(note.getUserId());
            NoteListVO noteListVO = new NoteListVO();
            noteListVO.setCreateTime(sdf.format(note.getCreateTime()));
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
}
