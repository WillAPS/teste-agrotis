package com.agrotis.testeagrotis.application.usecase;

import com.agrotis.testeagrotis.api.application.usecase.CadastrarPessoa;
import com.agrotis.testeagrotis.api.domain.Pessoa;
import com.agrotis.testeagrotis.api.infra.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CadastrarPessoaTest {

    private CadastrarPessoa cadastrarPessoa;
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void setUp() {
        pessoaRepository = mock(PessoaRepository.class);
        cadastrarPessoa = new CadastrarPessoa(pessoaRepository);
    }

    @Test
    void testSave() {
        Pessoa pessoaSimulada = new Pessoa();
        pessoaSimulada.setId(1L);
        pessoaSimulada.setNome("Nome de Teste");

        cadastrarPessoa.save(pessoaSimulada);

        ArgumentCaptor<Pessoa> captor = ArgumentCaptor.forClass(Pessoa.class);
        verify(pessoaRepository, times(1)).save(captor.capture());

        assertEquals(1L, captor.getValue().getId());
        assertEquals("Nome de Teste", captor.getValue().getNome());
    }

}
