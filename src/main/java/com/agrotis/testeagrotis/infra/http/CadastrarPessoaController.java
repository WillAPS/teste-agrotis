package com.agrotis.testeagrotis.infra.http;

import com.agrotis.testeagrotis.application.services.LaboratorioService;
import com.agrotis.testeagrotis.application.services.MessageErrorService;
import com.agrotis.testeagrotis.application.services.PropriedadeService;
import com.agrotis.testeagrotis.application.usecase.CadastrarPessoa;
import com.agrotis.testeagrotis.exception.LaboratorioNotFoundException;
import com.agrotis.testeagrotis.exception.PropriedadeNotFoundException;
import com.agrotis.testeagrotis.infra.http.dto.PessoaDTO;
import com.agrotis.testeagrotis.infra.http.dto.PessoaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
@Log4j2
public class CadastrarPessoaController {

    private final CadastrarPessoa cadastrarPessoa;
    private final LaboratorioService laboratorioService;
    private final PropriedadeService propriedadeService;
    private final MessageErrorService messageErrorService;
    private final PessoaMapper mapper;
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastraPessoa(@Valid @RequestBody PessoaDTO pessoaDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(messageErrorService.getErrosBindingResult(bindingResult));
        }

        var pessoaRecebida = mapper.map(pessoaDTO);
        var propriedadeRecebida = propriedadeService.buscarPropriedadePorId(pessoaRecebida.getIdPropriedade(), pessoaRecebida.getNomePropriedade());
        var laboratorioRecebido = laboratorioService.buscarLaboratorioPorId(pessoaRecebida.getIdLaboratorio(), pessoaRecebida.getNomeLaboratorio());

        if (propriedadeRecebida == null) {
            throw new PropriedadeNotFoundException();
        }

        if (laboratorioRecebido == null) {
            throw new LaboratorioNotFoundException();
        }

        laboratorioRecebido.aumentarQuantidadeDePessoas();
        laboratorioService.atualizarLaboratorio(laboratorioRecebido);
        cadastrarPessoa.save(pessoaRecebida);

        return ResponseEntity.ok("Pessoa cadastrada com sucesso");
    }
}
