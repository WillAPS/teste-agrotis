package com.agrotis.testeagrotis.api.application.usecase;

import com.agrotis.testeagrotis.api.domain.Pessoa;
import com.agrotis.testeagrotis.api.infra.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastrarPessoa {

    private PessoaRepository repository;
    public void save(Pessoa pessoa){
        repository.save(pessoa);
    }
}
