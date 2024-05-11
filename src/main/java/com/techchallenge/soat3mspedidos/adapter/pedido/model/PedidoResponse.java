package com.techchallenge.soat3mspedidos.adapter.pedido.model;

import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {

    private UUID id;
    private List<UUID> produtos;
    private UUID cliente;
    private StatusPedido statusPedido;
    private BigDecimal preco;
    private StatusPagamento statusPagamento;
    private LocalTime tempoPreparo;
    private String codigoPix;
    private String qrCode;
    private String idPagamentoMP;
}
