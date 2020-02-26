package com.crf.menu.exception;

import com.crf.menu.enums.StatusCode;

public class UserException extends BaseBusinessException {
    public UserException(StatusCode statusCode) {
        super(statusCode);
    }

    public UserException(int code, String msg) {
        super(code, msg);
    }
}
