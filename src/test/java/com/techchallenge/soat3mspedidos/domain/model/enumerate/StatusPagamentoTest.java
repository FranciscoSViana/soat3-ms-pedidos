package com.techchallenge.soat3mspedidos.domain.model.enumerate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatusPagamentoTest {

    @Test
    public void testPago() {
        String validName = "Pago";
        StatusPagamento statusPagamento = StatusPagamento.fromName(validName);
        assertEquals(StatusPagamento.PAGO, statusPagamento);
        assertEquals("Pago", statusPagamento.getDescricao());
    }

    @Test
    public void testInvalido() {
        String invalidName = "Invalido";
        assertThrows(IllegalArgumentException.class, () -> StatusPagamento.fromName(invalidName));
    }
}
