package com.crf.menu.exception;

import com.crf.menu.enums.StatusCode;

public class BaseBusinessException extends RuntimeException {
    private Integer code;

    public BaseBusinessException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.code = statusCode.getCode();
    }

    public BaseBusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
