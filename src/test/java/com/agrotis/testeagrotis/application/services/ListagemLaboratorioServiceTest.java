package com.agrotis.testeagrotis.application.services;

import com.agrotis.testeagrotis.api.application.services.ListagemLaboratorioService;
import com.agrotis.testeagrotis.api.domain.Laboratorio;
import com.agrotis.testeagrotis.api.infra.repository.LaboratorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ListagemLaboratorioServiceTest {

    private ListagemLaboratorioService listagemLaboratorioService;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listagemLaboratorioService = new ListagemLaboratorioService(laboratorioRepository);
    }

    @Test
    void testBuscarLaboratoriosFiltrados() {
        Integer quantidade = 10;
        Laboratorio laboratorio1 = new Laboratorio();
        laboratorio1.setId(1L);
        laboratorio1.setQuantidadePessoa(15);

        Laboratorio laboratorio2 = new Laboratorio();
        laboratorio2.setId(2L);
        laboratorio2.setQuantidadePessoa(5);

        List<Laboratorio> laboratorios = Arrays.asList(laboratorio1, laboratorio2);

        when(laboratorioRepository.findByQuantidadePessoaGreaterThanEqual(quantidade)).thenReturn(laboratorios);

        List<Laboratorio> resultados = listagemLaboratorioService.buscarLaboratoriosFiltrados(quantidade);

        verify(laboratorioRepository, times(1)).findByQuantidadePessoaGreaterThanEqual(quantidade);

        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        assertEquals(1L, resultados.get(0).getId());
        assertEquals(2L, resultados.get(1).getId());
    }

    @Test
    void testBuscarTodosLaboratorios() {
        var laboratorio1 = new Laboratorio();
        laboratorio1.setId(1L);

        var laboratorio2 = new Laboratorio();
        laboratorio2.setId(2L);

        var laboratorios = Arrays.asList(laboratorio1, laboratorio2);

        when(laboratorioRepository.findAll()).thenReturn(laboratorios);

        var resultados = listagemLaboratorioService.buscarTodosLaboratorios();

        verify(laboratorioRepository, times(1)).findAll();

        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        assertEquals(1L, resultados.get(0).getId());
        assertEquals(2L, resultados.get(1).getId());
    }

}
