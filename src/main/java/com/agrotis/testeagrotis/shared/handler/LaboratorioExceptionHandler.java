package com.agrotis.testeagrotis.shared.handler;

import com.agrotis.testeagrotis.shared.exception.LaboratorioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class LaboratorioExceptionHandler {

    @ExceptionHandler(LaboratorioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleLaboratorioNotFoundException(LaboratorioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
