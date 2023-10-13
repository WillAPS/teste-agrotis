package com.agrotis.testeagrotis.api.infra.http.dto;

import com.agrotis.testeagrotis.api.domain.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "dataInicial", source = "dataInicial")
    @Mapping(target = "dataFinal", source = "dataFinal")
    @Mapping(target = "idPropriedade", source = "infosPropriedade.id")
    @Mapping(target = "nomePropriedade", source = "infosPropriedade.nome")
    @Mapping(target = "idLaboratorio", source = "laboratorio.id")
    @Mapping(target = "nomeLaboratorio", source = "laboratorio.nome")
    @Mapping(target = "observacoes", source = "observacoes")
    Pessoa map(PessoaDTO pessoaDTO);
}
