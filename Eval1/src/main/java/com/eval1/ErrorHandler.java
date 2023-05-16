package com.eval1;


import com.eval1.exception.UnauthorizedException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    public String customError(UnauthorizedException e) {
        return "redirect:/security/check";
    }

}