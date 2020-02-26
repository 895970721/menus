package com.crf.menu.exception;

import com.crf.menu.enums.StatusCode;

public class MenuCollectException extends BaseBusinessException{
    public MenuCollectException(StatusCode statusCode) {
        super(statusCode);
    }

    public MenuCollectException(int code, String msg) {
        super(code, msg);
    }
}
