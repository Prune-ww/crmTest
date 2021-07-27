package com.ncu.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {
    //专门捕获运行时异常
    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public @ResponseBody Object exceptionHandler(Exception e){
        String message = e.getMessage();
        return message;
    }
}
