package com.crf.menu.vo;

import lombok.Data;

import java.util.Date;

@Data
public class NoteListVO {

    private Integer userId;

    private String photoAddress;

    private String nickName;

    private Integer noteId;

    private String noteName;

    private String noteImg;

    private String noteContent;

    private Integer likeNum;

    private String createTime;
}
