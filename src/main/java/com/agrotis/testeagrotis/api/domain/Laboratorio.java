package com.agrotis.testeagrotis.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "laboratorio")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(name = "quantidade_pessoa", nullable = false, columnDefinition = "INT DEFAULT 0")
    @JsonProperty("quantidade_pessoa")
    private Integer quantidadePessoa;

    public void aumentarQuantidadeDePessoas() {
        quantidadePessoa++;
    }

}
