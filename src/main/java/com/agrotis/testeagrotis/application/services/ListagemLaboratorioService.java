package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.domain.Laboratorio;
import com.agrotis.testeagrotis.infra.repository.LaboratorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListagemLaboratorioService {

    private LaboratorioRepository laboratorioRepository;

    public List<Laboratorio> buscarLaboratoriosFiltrados(Integer quantidade) {
        return laboratorioRepository.findByQuantidadePessoaGreaterThanEqual(quantidade);
    }
    public List<Laboratorio> buscarTodosLaboratorios(){
        return laboratorioRepository.findAll();
    }

}
