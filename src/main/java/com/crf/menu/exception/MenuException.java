package com.crf.menu.exception;

import com.crf.menu.enums.StatusCode;

public class MenuException extends BaseBusinessException {
    public MenuException(StatusCode statusCode) {
        super(statusCode);
    }

    public MenuException(int code, String msg) {
        super(code, msg);
    }
}
