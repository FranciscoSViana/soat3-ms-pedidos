package com.techchallenge.soat3mspedidos.application.pedido.model;

import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoResponseTest {

    @Test
    public void testPedidoResponse() {
        UUID id = UUID.randomUUID();
        List<UUID> produtos = new ArrayList<>();
        UUID cliente = UUID.randomUUID();
        StatusPedido statusPedido = StatusPedido.RECEBIDO;
        BigDecimal preco = BigDecimal.valueOf(50.00);
        StatusPagamento statusPagamento = StatusPagamento.PAGO;
        LocalTime tempoPreparo = LocalTime.of(0, 15);
        String codigoPix = "123456789";
        String qrCode = "ABCDEFG123456";
        String idPagamentoMP = "MP123456789";

        PedidoResponse response = PedidoResponse.builder()
                .id(id)
                .produtos(produtos)
                .cliente(cliente)
                .statusPedido(statusPedido)
                .preco(preco)
                .statusPagamento(statusPagamento)
                .tempoPreparo(tempoPreparo)
                .codigoPix(codigoPix)
                .qrCode(qrCode)
                .idPagamentoMP(idPagamentoMP)
                .build();

        assertEquals(id, response.getId());
        assertEquals(produtos, response.getProdutos());
        assertEquals(cliente, response.getCliente());
        assertEquals(statusPedido, response.getStatusPedido());
        assertEquals(preco, response.getPreco());
        assertEquals(statusPagamento, response.getStatusPagamento());
        assertEquals(tempoPreparo, response.getTempoPreparo());
        assertEquals(codigoPix, response.getCodigoPix());
        assertEquals(qrCode, response.getQrCode());
        assertEquals(idPagamentoMP, response.getIdPagamentoMP());
    }
}
