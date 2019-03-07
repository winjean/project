package com.winjean.common.exception;

import com.winjean.common.ResultConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String globalExceptionHandler(Exception e) throws Exception {
        ExceptionUtil.getTrace(e);
        return ResultConverter.parseError();
    }
}
