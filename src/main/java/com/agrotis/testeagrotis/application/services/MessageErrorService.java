package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.domain.ResponseErro;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MessageErrorService {
    public ResponseErro getErrosBindingResult(BindingResult bindingResult){
        var fieldErrors = bindingResult.getFieldErrors();
        var responseErro = new ResponseErro();

        fieldErrors.forEach(fieldError -> responseErro.adicionaErro(fieldError.getField(), fieldError.getDefaultMessage()));

        return responseErro;
    }
}
