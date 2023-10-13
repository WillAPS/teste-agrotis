package com.agrotis.testeagrotis.api.infra.http;

import com.agrotis.testeagrotis.api.application.services.ListagemLaboratorioService;
import com.agrotis.testeagrotis.api.domain.Laboratorio;
import com.agrotis.testeagrotis.api.domain.Pessoa;
import com.agrotis.testeagrotis.api.infra.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/laboratorio")
@AllArgsConstructor
@Log4j2
public class LaboratorioController {

    private final ListagemLaboratorioService listagemLaboratorioService;
    private final PessoaRepository pessoaRepository;
    @GetMapping("/listar")
    public ResponseEntity<List<Laboratorio>> listaLaboratorios(
            @RequestParam(value = "data_inicial_comeco", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicialStart,
            @RequestParam(value = "data_inicial_fim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicialEnd,
            @RequestParam(value = "data_final_comeco", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataFinalStart,
            @RequestParam(value = "data_final_fim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataFinalEnd,
            @RequestParam("quantidade_minima") Integer quantidadeMinima,
            @RequestParam(value = "palavra",required = false) String palavra,
            @RequestParam(value = "ordenar_quantidade", required = false) Boolean ordenarQuantidade,
            @RequestParam(value = "ordenar_data_inicial", required = false) Boolean ordenarDataInicial){

        List<Laboratorio> laboratorioList = quantidadeMinima != null
                ? listagemLaboratorioService.buscarLaboratoriosFiltrados(quantidadeMinima)
                : listagemLaboratorioService.buscarTodosLaboratorios();

        List<Pessoa> pessoaList = new ArrayList<>();
        if(palavra != null){
            var pessoasContemPalavra = pessoaRepository.findByObservacoesContendoPalavra(palavra);
            List<Pessoa> finalPessoaList = getFinalPessoaList(pessoaList, pessoasContemPalavra);
            laboratorioList.removeIf(laboratorio -> finalPessoaList.stream().noneMatch(pessoa -> Objects.equals(pessoa.getIdLaboratorio(), laboratorio.getId())));
        }

        if(dataInicialStart != null && dataInicialEnd != null){
            var pessoasDataInicialBetween = pessoaRepository.findByDataInicialBetween(dataInicialStart, dataInicialEnd);
            List<Pessoa> finalPessoaList = getFinalPessoaList(pessoaList, pessoasDataInicialBetween);
            laboratorioList.removeIf(laboratorio -> finalPessoaList.stream().noneMatch(pessoa -> Objects.equals(pessoa.getIdLaboratorio(), laboratorio.getId())));
        }

        if(dataFinalStart != null && dataFinalEnd != null){
            var pessoasDataFinalBetween = pessoaRepository.findByDataFinalBetween(dataFinalStart, dataFinalEnd);
            List<Pessoa> finalPessoaList = getFinalPessoaList(pessoaList, pessoasDataFinalBetween);
            laboratorioList.removeIf(laboratorio -> finalPessoaList.stream().noneMatch(pessoa -> Objects.equals(pessoa.getIdLaboratorio(), laboratorio.getId())));
        }

        if(ordenarQuantidade != null && ordenarQuantidade.equals(true)){
            laboratorioList.sort((lab1, lab2) -> Integer.compare(lab2.getQuantidadePessoa(), lab1.getQuantidadePessoa()));
        }

        if(ordenarDataInicial != null && ordenarDataInicial.equals(true)){
            pessoaList.sort(Comparator.comparing(Pessoa::getDataInicial));
        }

        laboratorioList.forEach(laboratorio -> laboratorio.setNome(laboratorio.getNome().toUpperCase()));
        return ResponseEntity.accepted().body(laboratorioList);
    }

    private static List<Pessoa> getFinalPessoaList(List<Pessoa> pessoaList, List<Pessoa> pessoas) {
        for (Pessoa pessoaOrigem : pessoas) {
            boolean idJaExiste = pessoaList.stream()
                    .anyMatch(item -> item.getId().equals(pessoaOrigem.getId()));
            if (!idJaExiste) {
                pessoaList.add(pessoaOrigem);
            }
        }
        return pessoaList;
    }
}
