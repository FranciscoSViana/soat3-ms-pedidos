package com.techchallenge.soat3mspedidos.application.pedido.converter;

import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.application.cliente.service.ClienteService;
import com.techchallenge.soat3mspedidos.application.produto.service.ProdutoService;
import com.techchallenge.soat3mspedidos.domain.model.PedidoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoMapper {

    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;

    private final ClienteService clienteService;


    public PagamentoModel convertPedidoToPagamento(PedidoModel pedido) {
        return modelMapper.map(pedido, PagamentoModel.PagamentoModelBuilder.class)
                .statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .build();
    }

    public PedidoModel convertPagamentoToPedido(PagamentoModel pagamento) {
        return modelMapper.map(pagamento, PedidoModel.PedidoModelBuilder.class)
                .build();
    }

    public PedidoResponse pedidoToPedidoRespose(PedidoModel pedido) {
        return PedidoResponse
                .builder()
                .id(pedido.getId())
                .cliente(pedido.getCliente().getId())
                .preco(pedido.getPreco())
                .statusPedido(pedido.getStatusPedido())
                .produtos(pedido.getProdutos().stream().map(ProdutoModel::getId).toList())
                .statusPagamento(pedido.getStatusPagamento())
                .tempoPreparo(pedido.getTempoPreparo())
                .codigoPix(pedido.getCodigoPix())
                .idPagamentoMP(pedido.getIdPagamentoMP())
                .build();
    }

    public PedidoModel pedidoRequestToPedidoModel(PedidoRequest pedidoRequest) {

        UUID id = (pedidoRequest.getId() != null) ? pedidoRequest.getId() : UUID.randomUUID();

        return PedidoModel
                .builder()
                .id(id)
                .tempoPreparo(pedidoRequest.getTempoPreparo())
                .cliente(clienteService.buscarPorId(pedidoRequest.getCliente()))
                .statusPedido(StatusPedido.RECEBIDO)
                .produtos(pedidoRequest.getProdutos()
                        .parallelStream()
                        .map(produtoId -> produtoService.getProduto(produtoId)).toList())
                .statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .build();
    }



    public List<PedidoResponse> pedidosToPedidosRespose(List<PedidoModel> pedidos) {
        return pedidos.stream().map((this::pedidoToPedidoRespose)).toList();
    }
}
