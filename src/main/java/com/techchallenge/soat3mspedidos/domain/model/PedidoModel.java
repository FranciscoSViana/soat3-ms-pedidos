package com.techchallenge.soat3mspedidos.domain.model;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pedidos")
public class PedidoModel {

    @Id
    private UUID id;

    private ClienteModel cliente;

    private StatusPedido statusPedido;

    private BigDecimal preco;

    private StatusPagamento statusPagamento;

    private LocalTime tempoPreparo;

    private String codigoPix;

    private String idPagamentoMP;

    private byte[] qrCode;

    private List<ProdutoModel> produtos;

}
