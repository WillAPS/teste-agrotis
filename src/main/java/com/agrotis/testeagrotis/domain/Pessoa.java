package com.agrotis.testeagrotis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "data_inicial")
    private Date dataInicial;
    @Column(name = "data_final")
    private Date dataFinal;
    @Column(name = "id_propriedade")
    private Long idPropriedade;
    @Column(name = "nome_propriedade")
    private String nomePropriedade;
    @Column(name = "id_laboratorio")
    private Long idLaboratorio;
    @Column(name = "nome_laboratorio")
    private String nomeLaboratorio;
    private String observacoes;
}
