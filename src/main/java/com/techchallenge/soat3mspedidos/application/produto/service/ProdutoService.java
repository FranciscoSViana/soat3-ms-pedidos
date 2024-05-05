package com.techchallenge.soat3mspedidos.application.produto.service;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    ProdutoModel getProduto(UUID id);

    List<ProdutoModel> obterProdutosPorIds(List<UUID> produtoIds);

}
