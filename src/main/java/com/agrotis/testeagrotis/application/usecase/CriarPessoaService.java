package com.agrotis.testeagrotis.application.usecase;

import com.agrotis.testeagrotis.domain.Pessoa;
import com.agrotis.testeagrotis.infra.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarPessoaService {

    private PessoaRepository repository;
    public void salvarPessoa(Pessoa pessoa){
        repository.save(pessoa);
    }
}
