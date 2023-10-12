package com.agrotis.testeagrotis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Caracteristicas {

    @JsonProperty("ordenar_quantidade")
    private Boolean ordenarQuantidade;
    @JsonProperty("quantidade_minima")
    private Integer qtdMinima;
}
