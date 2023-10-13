package com.agrotis.testeagrotis.api.infra.mysql;

import com.agrotis.testeagrotis.api.infra.repository.LaboratorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@AllArgsConstructor
public class InicializacaoBancoDados implements CommandLineRunner {
    private LaboratorioRepository repository;
    private final DataSource dataSource;
    @Override
    public void run(String... args) throws Exception {
        if(!repository.existsByNome("Agro Skynet")) {
            ClassPathResource resource = new ClassPathResource("inserts.sql");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
        }
    }
}