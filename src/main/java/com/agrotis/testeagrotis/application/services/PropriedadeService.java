package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.domain.Propriedade;
import com.agrotis.testeagrotis.infra.repository.PropriedadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropriedadeService {

    private final PropriedadeRepository propriedadeRepository;

    public Propriedade buscarPropriedadePorId(Long id, String nome) {
        Optional<Propriedade> propriedade= propriedadeRepository.findById(id);
        if(propriedade.isPresent() && !Objects.equals(propriedade.get().getNome(), nome)){
            return null;
        }
        return propriedade.orElse(null);
    }
}
