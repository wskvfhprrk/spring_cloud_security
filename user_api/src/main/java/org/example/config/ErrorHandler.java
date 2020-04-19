package org.example.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hejz
 * @Description: 处理异常错误
 * @Date: 2020/4/19 16:58
 */
@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handler(Exception e) {
        Map<String, Object> info = new HashMap<>();
        info.put("message", e.getMessage());
        info.put("time", new Date());
        return info;
    }
}
