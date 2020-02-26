package com.crf.menu.vo;

import lombok.Data;

@Data
public class CommentVO {
    private Integer id;

    private Integer noteId;

    private String content;

    private Integer fromUid;

    private String fromNickName;

    private String fromUserImage;

    private Integer toUid;

    private String toNickName;



    private String createTime;
}
