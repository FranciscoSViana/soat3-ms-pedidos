package com.techchallenge.soat3mspedidos.domain.model;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
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

    public void adicionarProdutoAoPedido(ProdutoModel produto) {
        this.produtos.add(produto);
        this.preco = this.preco.add(produto.getPreco());
    }
}
