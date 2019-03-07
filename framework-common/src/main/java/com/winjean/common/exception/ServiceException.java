package com.winjean.common.exception;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/7 18:55
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
