package com.agrotis.testeagrotis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

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
    @Column(name = "data_inicial", nullable = false)
    private Date dataInicial;
    @Column(name = "data_final", nullable = false)
    private Date dataFinal;
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
