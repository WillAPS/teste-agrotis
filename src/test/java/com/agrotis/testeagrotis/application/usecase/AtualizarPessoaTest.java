package com.agrotis.testeagrotis.application.usecase;

import com.agrotis.testeagrotis.api.application.usecase.AtualizarPessoa;
import com.agrotis.testeagrotis.api.domain.Pessoa;
import com.agrotis.testeagrotis.api.infra.repository.PessoaRepository;
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
        var id = 1L;

        var pessoaSimulada = new Pessoa();
        pessoaSimulada.setId(id);
        pessoaSimulada.setNome("Nome Original");

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaSimulada));

        Optional<Pessoa> pessoaEncontrada = atualizarPessoa.buscarPessoa(id);

        assertTrue(pessoaEncontrada.isPresent());
        assertEquals(id, pessoaEncontrada.get().getId());
        assertEquals("Nome Original", pessoaEncontrada.get().getNome());
    }

    @Test
    void testUpdate() {
        var pessoaSimulada = new Pessoa();
        pessoaSimulada.setId(1L);
        pessoaSimulada.setNome("Nome Original");

        when(pessoaRepository.save(pessoaSimulada)).thenReturn(pessoaSimulada);

        atualizarPessoa.update(pessoaSimulada);

        assertEquals("Nome Original", pessoaSimulada.getNome());
    }

}
