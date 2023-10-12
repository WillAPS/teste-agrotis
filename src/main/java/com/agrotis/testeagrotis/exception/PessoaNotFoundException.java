package com.agrotis.testeagrotis.exception;

public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException() {
        super("Pessoa não encontrada");
    }
}
