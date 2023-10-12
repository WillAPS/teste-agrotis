package com.agrotis.testeagrotis.infra.http.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
public class PessoaDTO {

    @NotBlank(message = "nome não pode estar branco ou nulo")
    @Size(max = 255)
    private String nome;

    @NotNull(message = "dataInicial não pode estar branco ou nulo")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDate dataInicial;

    @NotNull(message = "dataFinal não pode estar branco ou nulo")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDate dataFinal;

    @Valid
    @NotNull(message = "infosPropriedade não pode estar branco ou nulo")
    private PropriedadeDTO infosPropriedade;

    @Valid
    @NotNull(message = "laboratorio não pode estar branco ou nulo")
    private LaboratorioDTO laboratorio;

    @Size(max = 1000)
    private String observacoes;
    @AssertTrue(message = "A data final deve ser maior que a data inicial")
    public boolean isDataFinalMaiorQueInicial() {
        if (dataInicial == null || dataFinal == null) {
            return true;
        }
        return !dataFinal.isBefore(dataInicial);
    }

}
