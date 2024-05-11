package com.techchallenge.soat3mspedidos.application.pedido.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    @Test
    public void testHandleClienteNegocioException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Cliente não encontrado";
        NegocioException exception = new NegocioException(errorMessage);
        ResponseEntity<String> responseEntity = handler.handleClienteNegocioException(exception);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }
}
