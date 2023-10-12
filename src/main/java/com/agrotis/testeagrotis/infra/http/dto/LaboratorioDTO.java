package com.agrotis.testeagrotis.infra.http.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class LaboratorioDTO {

    @NotNull(message = "id não pode estar branco ou nulo")
    private Long id;

    @NotBlank(message = "nome não pode estar branco ou nulo")
    @Size(max = 255)
    private String nome;

}
