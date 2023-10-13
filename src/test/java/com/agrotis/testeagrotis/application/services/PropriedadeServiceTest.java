package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.api.application.services.PropriedadeService;
import com.agrotis.testeagrotis.api.domain.Propriedade;
import com.agrotis.testeagrotis.api.infra.repository.PropriedadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PropriedadeServiceTest {

    private PropriedadeService propriedadeService;
    private PropriedadeRepository propriedadeRepository;

    @BeforeEach
    public void setUp() {
        propriedadeRepository = mock(PropriedadeRepository.class);
        propriedadeService = new PropriedadeService(propriedadeRepository);
    }

    @Test
    void testBuscarPropriedadePorId() {
        var id = 1L;
        var nome = "Fazenda ABC";

        var propriedadeSimulada = new Propriedade();
        propriedadeSimulada.setId(id);
        propriedadeSimulada.setNome(nome);

        when(propriedadeRepository.findById(id)).thenReturn(Optional.of(propriedadeSimulada));

        var propriedadeEncontrada = propriedadeService.buscarPropriedadePorId(id, nome);

        assertEquals(id, propriedadeEncontrada.getId());
        assertEquals(nome, propriedadeEncontrada.getNome());
    }

}
