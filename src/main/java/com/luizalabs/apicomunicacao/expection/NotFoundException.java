package com.luizalabs.apicomunicacao.expection;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Informação não encontrada");
    }
}
