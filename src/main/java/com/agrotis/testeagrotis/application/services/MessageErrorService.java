package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.domain.ResponseErro;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MessageErrorService {
    public ResponseErro getErrosBindingResult(BindingResult bindingResult){
        var responseErro = new ResponseErro();
        var fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            responseErro.adicionaErro(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return responseErro;
    }
    public ResponseErro mensagemErro(String erro){
        var responseErro = new ResponseErro();
        responseErro.adicionaErro(erro, erro + " informado é inválido");
        return responseErro;
    }
}
