package com.agrotis.testeagrotis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErro {

    private String message = "Requisição Invalida";
    private final Map<String, List<String>> errors = new HashMap<>();
    public void adicionaErro(String campo, String message) {
        this.errors.put(campo, List.of(message));
    }
}
