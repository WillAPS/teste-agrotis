package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.domain.Laboratorio;
import com.agrotis.testeagrotis.infra.repository.LaboratorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class LaboratorioServiceTest {

    private LaboratorioService laboratorioService;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        laboratorioService = new LaboratorioService(laboratorioRepository);
    }

    @Test
    void testBuscarLaboratorioPorId() {
        Long id = 1L;
        String nome = "Laboratorio ABC";
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(id);
        laboratorio.setNome(nome);

        // Simulando o comportamento do laboratorioRepository
        when(laboratorioRepository.findById(id)).thenReturn(Optional.of(laboratorio));

        // Chamando o método do serviço
        Laboratorio resultado = laboratorioService.buscarLaboratorioPorId(id, nome);

        // Verificando se o laboratorioRepository foi chamado corretamente
        verify(laboratorioRepository, times(1)).findById(id);

        // Verificando se o resultado é o laboratório esperado
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
    }

    @Test
    void testAtualizarLaboratorio() {
        Laboratorio laboratorio = new Laboratorio();

        // Chamando o método do serviço
        laboratorioService.atualizarLaboratorio(laboratorio);

        // Verificando se o laboratorioRepository foi chamado para salvar o laboratório
        verify(laboratorioRepository, times(1)).save(eq(laboratorio));
    }

}
