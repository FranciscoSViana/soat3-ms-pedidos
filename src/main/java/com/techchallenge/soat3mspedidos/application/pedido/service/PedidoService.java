package com.techchallenge.soat3mspedidos.application.pedido.service;




import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.domain.model.PedidoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoService {
    PedidoResponse salvar(PedidoRequest request);

    PedidoResponse pagar(UUID id);

    PedidoResponse alterarStatus(UUID id, StatusPedido status);

    PedidoResponse buscarPedido(UUID id);

    List<PedidoResponse> buscarPedidos();

    PedidoResponse criarPagamento(UUID id);

    PedidoModel obterPorIdPagamentoMP(String idPagamento);

    PedidoModel confirmarPagamento(PedidoModel pedido);

    PedidoModel salvar(PedidoModel pedido);

    Optional<PedidoModel> obterPorUUID(String idPagamento);

    List<PedidoModel> obterPedidosComPagamentoAguardando();


}
