package com.agrotis.testeagrotis.application.services;


import com.agrotis.testeagrotis.api.application.services.MessageErrorService;
import com.agrotis.testeagrotis.api.domain.MensagemErro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MensagemErrorServiceTest {

    private MessageErrorService messageErrorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        messageErrorService = new MessageErrorService();
    }

    @Test
    void testGetErrosBindingResult() {
        BindingResult bindingResult = mock(BindingResult.class);

        List<FieldError> fieldErrors = List.of(
                new FieldError("objeto", "campo1", "Mensagem de erro 1"),
                new FieldError("objeto", "campo2", "Mensagem de erro 2")
        );

        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        MensagemErro mensagemErro = messageErrorService.getErrosBindingResult(bindingResult);

        Map<String, List<String>> errors = mensagemErro.getErrors();
        assertEquals(2, errors.size());
        assertEquals("Mensagem de erro 1", errors.get("campo1").get(0));
        assertEquals("Mensagem de erro 2", errors.get("campo2").get(0));
    }
}
