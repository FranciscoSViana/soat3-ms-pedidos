package com.techchallenge.soat3mspedidos.domain.model.enumerate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusPedidoTest {

    @Test
    public void testStatusPedido() {
        String expectedReceived = "Recebido";
        String expectedPreparing = "Em Preparacao";
        String expectedReady = "Pronto";
        String expectedCompleted = "Finalizado";

        String received = StatusPedido.RECEBIDO.getStatus();
        String preparing = StatusPedido.EM_PREPARACAO.getStatus();
        String ready = StatusPedido.PRONTO.getStatus();
        String completed = StatusPedido.FINALIZADO.getStatus();

        assertEquals(expectedReceived, received);
        assertEquals(expectedPreparing, preparing);
        assertEquals(expectedReady, ready);
        assertEquals(expectedCompleted, completed);
    }
}
