package com.agrotis.testeagrotis.application.usecase;

import com.agrotis.testeagrotis.domain.Pessoa;
import com.agrotis.testeagrotis.infra.repository.PessoaRepository;
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
        // Crie uma instância simulada de Pessoa
        Pessoa pessoaSimulada = new Pessoa();
        pessoaSimulada.setId(1L);
        pessoaSimulada.setNome("Nome de Teste");

        // Chame o método a ser testado
        cadastrarPessoa.save(pessoaSimulada);

        // Use ArgumentCaptor para capturar o objeto que foi passado para repository.save
        ArgumentCaptor<Pessoa> captor = ArgumentCaptor.forClass(Pessoa.class);
        verify(pessoaRepository, times(1)).save(captor.capture());

        // Verifique se o objeto capturado corresponde à instância simulada
        assertEquals(1L, captor.getValue().getId());
        assertEquals("Nome de Teste", captor.getValue().getNome());
    }

}
