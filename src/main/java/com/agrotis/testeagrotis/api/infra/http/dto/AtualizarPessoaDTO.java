package com.agrotis.testeagrotis.api.infra.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AtualizarPessoaDTO {


    @NotNull(message = "id não pode estar branco ou nulo")
    private Long id;
    @NotBlank(message = "novo_nome não pode estar branco ou nulo")
    @JsonProperty("novo_nome")
    private String nome;

}
