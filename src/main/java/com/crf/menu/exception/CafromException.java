package com.crf.menu.exception;

import com.crf.menu.enums.StatusCode;

public class CafromException extends BaseBusinessException {
    public CafromException(StatusCode statusCode) {
        super(statusCode);
    }

    public CafromException(int code, String msg) {
        super(code, msg);
    }
}
