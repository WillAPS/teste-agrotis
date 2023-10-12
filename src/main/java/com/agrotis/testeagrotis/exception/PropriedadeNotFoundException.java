package com.agrotis.testeagrotis.exception;

public class PropriedadeNotFoundException extends RuntimeException {
    public PropriedadeNotFoundException() {
        super("Propriedade não encontrada");
    }
}
