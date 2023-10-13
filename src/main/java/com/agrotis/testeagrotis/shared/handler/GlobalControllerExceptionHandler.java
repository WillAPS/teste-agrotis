package com.agrotis.testeagrotis.shared.handler;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return "Parâmetro ausente: " + ex.getParameterName();
    }
}