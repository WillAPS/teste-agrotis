package com.agrotis.testeagrotis.api.infra.repository;

import com.agrotis.testeagrotis.api.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT e FROM pessoa e WHERE e.observacoes LIKE %:palavra%")
    List<Pessoa> findByObservacoesContendoPalavra(@Param("palavra") String palavra);
    List<Pessoa> findByDataInicialBetween(Date start, Date end);
    List<Pessoa> findByDataFinalBetween(Date start, Date end);
}
