package com.agrotis.testeagrotis.shared.exception;

public class LaboratorioNotFoundException extends RuntimeException {
    public LaboratorioNotFoundException() {
        super("Laboratório não encontrado");
    }
}
