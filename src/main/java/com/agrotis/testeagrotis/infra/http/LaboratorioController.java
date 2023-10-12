package com.agrotis.testeagrotis.infra.http;

import com.agrotis.testeagrotis.application.services.LaboratorioService;
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

    private final LaboratorioService laboratorioService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Laboratorio>> getLaboratorios(@RequestBody Caracteristicas caracteristicas){

        var laboratorios = laboratorioService.buscarLaboratorios(caracteristicas);

        if(caracteristicas.getOrdenado() != null && caracteristicas.getOrdenado()){
            laboratorios = laboratorioService.ordenaLaboratorios(laboratorios);
        }

        return ResponseEntity.accepted().body(laboratorios);
    }
}
