package com.winjean.common.exception;

import com.winjean.common.ResultConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 项目名称：重庆微警务（一期）
 * 类名称：<....>
 * 类描述：<....>
 * 创建人：winjean
 * 创建时间：2018/11/16 16:21
 * 修改人：winjean
 * 修改时间：2018/11/16 16:21
 * 修改备注：
 * 版权所有权：
 *
 * @version V1.0
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
