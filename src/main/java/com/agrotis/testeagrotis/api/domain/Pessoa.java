package com.agrotis.testeagrotis.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicial")
    private Date dataInicial;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_final")
    private java.util.Date dataFinal;
    @Column(name = "id_propriedade", nullable = false)
    private Long idPropriedade;
    @Column(name = "nome_propriedade", nullable = false)
    private String nomePropriedade;
    @Column(name = "id_laboratorio", nullable = false)
    private Long idLaboratorio;
    @Column(name = "nome_laboratorio", nullable = false)
    private String nomeLaboratorio;
    private String observacoes;
}
