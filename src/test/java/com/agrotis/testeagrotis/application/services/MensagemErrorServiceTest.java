package com.agrotis.testeagrotis.application.services;


import com.agrotis.testeagrotis.domain.ResponseErro;
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
        // Crie um mock para BindingResult
        BindingResult bindingResult = mock(BindingResult.class);

        // Crie alguns erros de campo simulados
        List<FieldError> fieldErrors = List.of(
                new FieldError("objeto", "campo1", "Mensagem de erro 1"),
                new FieldError("objeto", "campo2", "Mensagem de erro 2")
        );

        // Simule o comportamento de getFieldErrors no BindingResult
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // Chame o método getErrosBindingResult
        ResponseErro responseErro = messageErrorService.getErrosBindingResult(bindingResult);

        // Verifique se a ResponseErro contém os erros esperados
        Map<String, List<String>> errors = responseErro.getErrors();
        assertEquals(2, errors.size());
        assertEquals("Mensagem de erro 1", errors.get("campo1").get(0));
        assertEquals("Mensagem de erro 2", errors.get("campo2").get(0));
    }
}
