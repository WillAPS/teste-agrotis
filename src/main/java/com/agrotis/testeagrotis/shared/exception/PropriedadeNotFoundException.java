package com.agrotis.testeagrotis.shared.exception;

public class PropriedadeNotFoundException extends RuntimeException {
    public PropriedadeNotFoundException() {
        super("Propriedade não encontrada");
    }
}
