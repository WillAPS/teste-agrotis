package com.agrotis.testeagrotis.api.infra.repository;

import com.agrotis.testeagrotis.api.domain.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    boolean existsByNome(String nome);
    List<Laboratorio> findByQuantidadePessoaGreaterThanEqual(Integer qtdMinima);
}
