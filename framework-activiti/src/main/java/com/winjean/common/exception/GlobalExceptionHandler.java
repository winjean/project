package com.winjean.common.exception;

import com.winjean.common.ResultConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * ：2018/11/16 16:21
 *
 * 6 16:21
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String globalExceptionHandler(Exception e) throws Exception {
        ExceptionUtil.getTrace(e);
        return ResultConverter.parseError();
    }
}
