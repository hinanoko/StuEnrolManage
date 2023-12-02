package com.example.springbootfirst.exception;

import com.example.springbootfirst.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Result<?> handleLoginException(LoginException e) {
        return Result.error(e.getMessage());
    }

}
