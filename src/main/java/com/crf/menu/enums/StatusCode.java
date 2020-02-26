package com.crf.menu.enums;

public enum StatusCode {
    Success(0, "成功"),
    Fail(1,"失败"),

    UserNoLogin(10001,"用户未登录"),
    UserLongTimeNoLogin(10002,"用户长时间未登录"),
    UserNameNoExit(10003,"用户名不存在"),
    UserPasswordError(10004,"用户密码错误"),
    UserNameIllegal(10005,"用户名非法"),
    PasswordIllegal(10006,"用户密码非法"),
    UserNameExit(10007,"用户名已存在"),

    UploadImageError(10011,"上传图片失败"),

    TokenLenError(20001,"token长度非法"),

    MenuNoExit(30001,"菜谱不存在"),

    CafromNoExit(40001,"菜谱小类不存在"),

    MenuCollectNoExit(50001,"菜谱收藏不存在"),
    MenuCollectExit(50002,"菜谱收藏已存在"),

    NoteLikeNoExit(60001,"笔记喜欢已存在"),
    NoteLikeExit(60002,"笔记喜欢已存在");

    final private Integer Code;
    final private String msg;

    StatusCode(Integer Code, String msg) {
        this.Code = Code;
        this.msg = msg;
    }

    public Integer getCode() {
        return Code;
    }

    public String getMsg() {
        return msg;
    }

}
