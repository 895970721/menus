package com.crf.menu.handler;

import com.crf.menu.enums.StatusCode;
import com.crf.menu.exception.BaseBusinessException;
import com.crf.menu.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 控制器的异常处理类
 * @author funnyChen
 */
//这个注解是指这个类是处理其他controller抛出的异常
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    //这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
    @ExceptionHandler(BaseBusinessException.class)
    public BaseResponse handlerException(BaseBusinessException ex){
        log.error(ex.toString());
        BaseResponse response = new BaseResponse(ex.getCode(),ex.getMessage());
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handlerException(ConstraintViolationException ex){
        log.error(ex.toString());
        BaseResponse response = new BaseResponse(StatusCode.Fail.getCode(),ex.getMessage());
        return response;
    }
}
