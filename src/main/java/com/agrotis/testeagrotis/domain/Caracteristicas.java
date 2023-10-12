package com.agrotis.testeagrotis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Caracteristicas {

    private Boolean ordenado;
    @JsonProperty("quantidade_minima")
    private Integer qtdMinima;

}
