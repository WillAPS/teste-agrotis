package com.agrotis.testeagrotis.api.application.services;

import com.agrotis.testeagrotis.api.domain.Propriedade;
import com.agrotis.testeagrotis.api.infra.repository.PropriedadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class PropriedadeService {

    private final PropriedadeRepository propriedadeRepository;

    public Propriedade buscarPropriedadePorId(Long id, String nome) {
        return propriedadeRepository.findById(id)
                .filter(propriedade -> Objects.equals(propriedade.getNome(), nome))
                .orElse(null);
    }
}
