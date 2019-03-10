package com.winjean.aspect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winjean.enums.DateTimeEnum;
import com.winjean.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {
    // ..表示包及子包 该方法代表controller层的所有方法
    @Pointcut("execution(public * com.winjean.controller..*.*(..))")
    public void controllerMethod() {
    }

    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuffer requestLog = new StringBuffer();
        requestLog.append("请求信息：")
                .append(DateUtils.getDateTime(DateTimeEnum.dateTime11.getValue()))
                .append(" --> URL = {" + request.getRequestURI() + "}, ")
                .append("HTTP_METHOD = {" + request.getMethod() + "}, ")
                .append("IP = {" + request.getRemoteAddr() + "}, ")
                .append("CLASS_METHOD = {" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "},\t");

        if(joinPoint.getArgs().length == 0) {
            requestLog.append("ARGS = {} ");
        } else {
            requestLog.append("ARGS = " + new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writeValueAsString(joinPoint.getArgs()[0]));
        }

        log.info(requestLog.toString());
    }


    @AfterReturning(returning = "returnValue", pointcut = "controllerMethod()")
    public void logResultVOInfo(JoinPoint point, Object returnValue) throws Exception {
        log.info("请求结果：{}", returnValue);
    }
}
