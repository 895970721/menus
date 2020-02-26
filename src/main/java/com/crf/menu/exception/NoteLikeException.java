package com.crf.menu.exception;

import com.crf.menu.enums.StatusCode;

public class NoteLikeException extends BaseBusinessException {

    public NoteLikeException(StatusCode statusCode){
        super(statusCode);
    }

    public NoteLikeException(int code, String msg) {
        super(code, msg);
    }
}
