package com.indentados.clinicaodonto.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }  public ResourceNotFoundException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }
}
