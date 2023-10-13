package com.agrotis.testeagrotis.shared.exception;

public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException() {
        super("Pessoa não encontrada");
    }
}
