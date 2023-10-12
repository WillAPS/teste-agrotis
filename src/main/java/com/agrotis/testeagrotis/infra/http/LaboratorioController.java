package com.agrotis.testeagrotis.infra.http;

import com.agrotis.testeagrotis.application.services.ListagemLaboratorioService;
import com.agrotis.testeagrotis.domain.Caracteristicas;
import com.agrotis.testeagrotis.domain.Laboratorio;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/laboratorio")
@AllArgsConstructor
@Log4j2
public class LaboratorioController {

    private final ListagemLaboratorioService listagemLaboratorioService;
    @GetMapping("/buscar")
    public ResponseEntity<List<Laboratorio>> getLaboratorios(@RequestBody Caracteristicas caracteristicas){

        List<Laboratorio> laboratoriosList = caracteristicas.getQtdMinima() != null
                ? listagemLaboratorioService.buscarLaboratoriosFiltrados(caracteristicas.getQtdMinima())
                : listagemLaboratorioService.buscarTodosLaboratorios();

        if(caracteristicas.getOrdenarQuantidade() != null){
            laboratoriosList.sort((lab1, lab2) -> Integer.compare(lab2.getQuantidadePessoa(), lab1.getQuantidadePessoa()));
        }

        laboratoriosList.forEach(laboratorio -> laboratorio.setNome(laboratorio.getNome().toUpperCase()));
        return ResponseEntity.accepted().body(laboratoriosList);
    }
}
