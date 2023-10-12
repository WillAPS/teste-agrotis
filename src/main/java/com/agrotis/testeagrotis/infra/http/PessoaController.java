package com.agrotis.testeagrotis.infra.http;

import com.agrotis.testeagrotis.application.services.LaboratorioService;
import com.agrotis.testeagrotis.application.services.MessageErrorService;
import com.agrotis.testeagrotis.application.services.PropriedadeService;
import com.agrotis.testeagrotis.application.usecase.CriarPessoaService;
import com.agrotis.testeagrotis.domain.ResponseSuccess;
import com.agrotis.testeagrotis.exception.LaboratorioNotFoundException;
import com.agrotis.testeagrotis.exception.PropriedadeNotFoundException;
import com.agrotis.testeagrotis.infra.http.dto.PessoaDTO;
import com.agrotis.testeagrotis.infra.http.dto.PessoaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
@Log4j2
public class PessoaController {

    private final CriarPessoaService criarPessoaService;
    private final LaboratorioService laboratorioService;
    private final PropriedadeService propriedadeService;
    private final MessageErrorService messageErrorService;
    private final PessoaMapper mapper;
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastraPessoa(@Valid @RequestBody PessoaDTO pessoaDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(messageErrorService.getErrosBindingResult(bindingResult));
        }

        try {

            var pessoaRecebida = mapper.map(pessoaDTO);

            var propriedadeRecebida = propriedadeService.buscarPropriedadePorId(pessoaRecebida.getIdPropriedade(), pessoaRecebida.getNomePropriedade());
            if (propriedadeRecebida == null) {
                throw new PropriedadeNotFoundException();
            }

            var laboratorioRecebido = laboratorioService.buscarLaboratorioPorId(pessoaRecebida.getIdLaboratorio(), pessoaRecebida.getNomeLaboratorio());
            if (laboratorioRecebido == null) {
                throw new LaboratorioNotFoundException();
            }

            laboratorioRecebido.aumentarQuantidadeDePessoas();
            laboratorioService.atualizarLaboratorio(laboratorioRecebido);

            criarPessoaService.salvarPessoa(pessoaRecebida);

            return ResponseEntity.accepted().body(new ResponseSuccess());
        } catch (PropriedadeNotFoundException | LaboratorioNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
