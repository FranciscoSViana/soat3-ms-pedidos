package com.techchallenge.soat3mspedidos.application.produto.service;

import com.techchallenge.soat3mspedidos.adapter.cliente.ClienteClient;
import com.techchallenge.soat3mspedidos.adapter.produto.ProdutoClient;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import com.techchallenge.soat3mspedidos.application.pedido.exception.NegocioException;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.TipoCategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoClient produtoClient;


    @Override
    public ProdutoModel getProduto(UUID id) {

        try {

            ProdutoModel produto = produtoClient.getProduto(id).getBody();

            return produto;

        }catch(Exception err){
            throw new NegocioException(format("Erro ao obter produto: ",err.getMessage()));
        }

    }

    @Override
    public List<ProdutoModel> obterProdutosPorIds(List<UUID> produtoIds) {
        try {

            ResponseEntity<List<ProdutoModel>> produtos = produtoClient.obterProdutosPorIDs(produtoIds);

            return produtos.getBody();

        }catch(Exception err){
            throw new NegocioException(format("Erro ao obter produto: ",err.getMessage()));
        }
    }


}
