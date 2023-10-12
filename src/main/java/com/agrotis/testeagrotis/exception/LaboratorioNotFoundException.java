package com.agrotis.testeagrotis.exception;

public class LaboratorioNotFoundException extends RuntimeException {
    public LaboratorioNotFoundException() {
        super("Laboratório não encontrado");
    }
}
