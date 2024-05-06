package com.techchallenge.soat3mspedidos.adapter.produto;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "produtoClient", url = "${produto.url}")
public interface ProdutoClient {

    @GetMapping(value = "/{produtoId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<ProdutoModel> getProduto(@PathVariable UUID produtoId);

    @GetMapping(value = "/produtos/", consumes = "application/json", produces = "application/json")
    ResponseEntity<List<ProdutoModel>> obterProdutosPorIDs(@RequestParam List<UUID> produtosIds);




}
