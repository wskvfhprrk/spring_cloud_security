package com.hejz.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常错误处理——但是处理完之后日志中不会打印异常信息
 */
@RestControllerAdvice
public class ErrorHandler {

    //无论什么异常就抛出内部异常错误500状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handle(Exception ex) {
        //如果需要输出错误日志，需要在此打印错误日志
        System.out.println("错误信息是："+ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("time", new Date());
        return map;
    }
}
