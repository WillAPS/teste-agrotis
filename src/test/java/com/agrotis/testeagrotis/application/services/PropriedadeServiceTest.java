package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.domain.Propriedade;
import com.agrotis.testeagrotis.infra.repository.PropriedadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PropriedadeServiceTest {

    private PropriedadeService propriedadeService;
    private PropriedadeRepository propriedadeRepository;

    @BeforeEach
    public void setUp() {
        propriedadeRepository = mock(PropriedadeRepository.class);
        propriedadeService = new PropriedadeService(propriedadeRepository);
    }

    @Test
    public void testBuscarPropriedadePorId() {
        Long id = 1L;
        String nome = "Fazenda ABC";

        // Crie uma instância de Propriedade simulada para retornar no mock do repository
        Propriedade propriedadeSimulada = new Propriedade();
        propriedadeSimulada.setId(id);
        propriedadeSimulada.setNome(nome);

        // Simule o comportamento do método findById no repositório
        when(propriedadeRepository.findById(id)).thenReturn(Optional.of(propriedadeSimulada));

        // Chame o método a ser testado
        Propriedade propriedadeEncontrada = propriedadeService.buscarPropriedadePorId(id, nome);

        // Verifique se a propriedade encontrada corresponde à propriedade simulada
        assertEquals(id, propriedadeEncontrada.getId());
        assertEquals(nome, propriedadeEncontrada.getNome());
    }

}
