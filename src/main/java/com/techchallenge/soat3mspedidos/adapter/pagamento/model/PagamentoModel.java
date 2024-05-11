package com.techchallenge.soat3mspedidos.adapter.pagamento.model;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoModel {

    private UUID id;

    private ClienteModel cliente;

    private BigDecimal preco;

    private StatusPagamento statusPagamento;

    private String codigoPix;

    private String idPagamentoMP;

    private byte[] qrCode;

}
