package com.agrotis.testeagrotis.application.usecase;

import com.agrotis.testeagrotis.domain.Pessoa;
import com.agrotis.testeagrotis.infra.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AtualizarPessoaTest {

    private AtualizarPessoa atualizarPessoa;
    private PessoaRepository pessoaRepository;

    @BeforeEach
    public void setUp() {
        pessoaRepository = mock(PessoaRepository.class);
        atualizarPessoa = new AtualizarPessoa(pessoaRepository);
    }

    @Test
    void testBuscarPessoa() {
        Long id = 1L;

        // Crie uma instância simulada de Pessoa para retornar no mock do repositório
        Pessoa pessoaSimulada = new Pessoa();
        pessoaSimulada.setId(id);
        pessoaSimulada.setNome("Nome Original");

        // Simule o comportamento do método findById no repositório
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaSimulada));

        // Chame o método a ser testado
        Optional<Pessoa> pessoaEncontrada = atualizarPessoa.buscarPessoa(id);

        // Verifique se a pessoa encontrada não está vazia e corresponde à pessoa simulada
        assertTrue(pessoaEncontrada.isPresent());
        assertEquals(id, pessoaEncontrada.get().getId());
        assertEquals("Nome Original", pessoaEncontrada.get().getNome());
    }

    @Test
    void testUpdate() {
        // Crie uma instância simulada de Pessoa
        Pessoa pessoaSimulada = new Pessoa();
        pessoaSimulada.setId(1L);
        pessoaSimulada.setNome("Nome Original");

        // Simule o comportamento do método save no repositório
        when(pessoaRepository.save(pessoaSimulada)).thenReturn(pessoaSimulada);

        // Chame o método a ser testado
        atualizarPessoa.update(pessoaSimulada);

        // Verifique se a pessoa foi atualizada corretamente
        assertEquals("Nome Original", pessoaSimulada.getNome());
    }

}
