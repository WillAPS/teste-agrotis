package com.agrotis.testeagrotis.api.application.services;

import com.agrotis.testeagrotis.api.domain.MensagemErro;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MessageErrorService {
    public MensagemErro getErrosBindingResult(BindingResult bindingResult){
        var fieldErrors = bindingResult.getFieldErrors();
        var responseErro = new MensagemErro();

        fieldErrors.forEach(fieldError -> responseErro.adicionaErro(fieldError.getField(), fieldError.getDefaultMessage()));

        return responseErro;
    }
}
