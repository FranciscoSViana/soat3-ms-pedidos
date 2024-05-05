package com.techchallenge.soat3mspedidos.application.pedido.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
