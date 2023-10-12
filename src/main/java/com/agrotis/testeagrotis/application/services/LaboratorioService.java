package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.domain.Caracteristicas;
import com.agrotis.testeagrotis.domain.Laboratorio;
import com.agrotis.testeagrotis.infra.repository.LaboratorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LaboratorioService {

    private LaboratorioRepository laboratorioRepository;

    public Laboratorio buscarLaboratorioPorId(Long id, String nome) {
        Optional<Laboratorio> laboratorioOptional = laboratorioRepository.findById(id);
        if(laboratorioOptional.isPresent() && !Objects.equals(laboratorioOptional.get().getNome(), nome)){
            return null;
        }
        return laboratorioOptional.orElse(null);
    }
    public void atualizarLaboratorio(Laboratorio lab){
        laboratorioRepository.save(lab);
    }
    public List<Laboratorio> buscarLaboratorios(Caracteristicas caracteristicas) {

        List<Laboratorio> labs = caracteristicas.getQtdMinima() != null
                ? laboratorioRepository.findByQuantidadePessoaGreaterThanEqual(caracteristicas.getQtdMinima())
                : laboratorioRepository.findAll();

        labs.forEach(lab -> lab.setNome(lab.getNome().toUpperCase()));
        return labs;
    }
    public List<Laboratorio> ordenaLaboratorios(List<Laboratorio> laboratorioList) {
        laboratorioList.sort((lab1, lab2) -> Integer.compare(lab2.getQuantidadePessoa(), lab1.getQuantidadePessoa()));
        return laboratorioList;
    }
}
