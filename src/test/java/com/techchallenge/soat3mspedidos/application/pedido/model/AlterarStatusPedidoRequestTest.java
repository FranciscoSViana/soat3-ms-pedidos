package com.techchallenge.soat3mspedidos.application.pedido.model;

import com.techchallenge.soat3mspedidos.adapter.pedido.model.AlterarStatusPedidoRequest;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlterarStatusPedidoRequestTest {

    @Test
    public void testAlterarStatusPedidoRequest() {
        StatusPedido status = StatusPedido.PRONTO;
        AlterarStatusPedidoRequest request = AlterarStatusPedidoRequest.builder()
                .status(status)
                .build();

        assertEquals(status, request.getStatus());
    }
}
