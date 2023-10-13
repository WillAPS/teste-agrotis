package com.agrotis.testeagrotis.api.application.services;

import com.agrotis.testeagrotis.api.domain.Laboratorio;
import com.agrotis.testeagrotis.api.infra.repository.LaboratorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class LaboratorioService {

    private LaboratorioRepository laboratorioRepository;

    public Laboratorio buscarLaboratorioPorId(Long id, String nome) {
        return laboratorioRepository.findById(id)
                .filter(laboratorio -> Objects.equals(laboratorio.getNome(), nome))
                .orElse(null);
    }
    public void atualizarLaboratorio(Laboratorio lab){
        laboratorioRepository.save(lab);
    }
}
