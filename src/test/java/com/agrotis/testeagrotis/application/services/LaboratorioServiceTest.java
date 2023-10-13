package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.api.application.services.LaboratorioService;
import com.agrotis.testeagrotis.api.domain.Laboratorio;
import com.agrotis.testeagrotis.api.infra.repository.LaboratorioRepository;
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
        var id = 1L;
        var nome = "Laboratorio ABC";
        var laboratorio = new Laboratorio();
        laboratorio.setId(id);
        laboratorio.setNome(nome);

        when(laboratorioRepository.findById(id)).thenReturn(Optional.of(laboratorio));

        var resultado = laboratorioService.buscarLaboratorioPorId(id, nome);

        verify(laboratorioRepository, times(1)).findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
    }

    @Test
    void testAtualizarLaboratorio() {
        var laboratorio = new Laboratorio();

        laboratorioService.atualizarLaboratorio(laboratorio);

        verify(laboratorioRepository, times(1)).save(eq(laboratorio));
    }

}
