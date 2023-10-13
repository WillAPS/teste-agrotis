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
        Long id = 1L;
        String nome = "Laboratorio ABC";
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(id);
        laboratorio.setNome(nome);

        when(laboratorioRepository.findById(id)).thenReturn(Optional.of(laboratorio));

        Laboratorio resultado = laboratorioService.buscarLaboratorioPorId(id, nome);

        verify(laboratorioRepository, times(1)).findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
    }

    @Test
    void testAtualizarLaboratorio() {
        Laboratorio laboratorio = new Laboratorio();

        laboratorioService.atualizarLaboratorio(laboratorio);

        verify(laboratorioRepository, times(1)).save(eq(laboratorio));
    }

}
