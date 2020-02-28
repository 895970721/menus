package com.crf.menu.exception;

import com.crf.menu.enums.StatusCode;

public class NoteException extends BaseBusinessException {
    public NoteException(StatusCode statusCode) {
        super(statusCode);
    }

    public NoteException(int code, String msg) {
        super(code, msg);
    }
}
