package com.agrotis.testeagrotis.infra.http;

import com.agrotis.testeagrotis.application.services.MessageErrorService;
import com.agrotis.testeagrotis.application.usecase.AtualizarPessoa;
import com.agrotis.testeagrotis.exception.PessoaNotFoundException;
import com.agrotis.testeagrotis.infra.http.dto.AtualizarPessoaDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
@Log4j2
public class AtualizarPessoaController {

    private final AtualizarPessoa atualizarPessoa;
    private final MessageErrorService messageErrorService;

    @PatchMapping("/atualizar")
    public ResponseEntity<Object> atualizaPessoa(@Valid @RequestBody AtualizarPessoaDTO atualizarPessoaDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(messageErrorService.getErrosBindingResult(bindingResult));
        }

        var pessoaRecebida = atualizarPessoa.buscarPessoa(atualizarPessoaDTO.getId())
                .orElseThrow(PessoaNotFoundException::new);

        pessoaRecebida.setNome(atualizarPessoaDTO.getNome());
        atualizarPessoa.update(pessoaRecebida);

        return ResponseEntity.ok("Pessoa atualizada com sucesso");
    }

}
