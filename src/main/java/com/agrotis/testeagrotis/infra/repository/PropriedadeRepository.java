package com.agrotis.testeagrotis.infra.repository;

import com.agrotis.testeagrotis.domain.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
}
