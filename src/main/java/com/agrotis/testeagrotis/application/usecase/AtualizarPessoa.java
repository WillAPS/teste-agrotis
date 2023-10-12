package com.agrotis.testeagrotis.application.usecase;

import com.agrotis.testeagrotis.domain.Pessoa;
import com.agrotis.testeagrotis.infra.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AtualizarPessoa {

    private PessoaRepository repository;
    public Optional<Pessoa> buscarPessoa(Long id){
        return repository.findById(id);
    }
    public void update(Pessoa pessoa){
        repository.save(pessoa);
    }

}
