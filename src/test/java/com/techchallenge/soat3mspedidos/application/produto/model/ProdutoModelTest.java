package com.techchallenge.soat3mspedidos.application.produto.model;

import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoModelTest {

    @Test
    public void testProdutoModel() {
        UUID id = UUID.randomUUID();
        String nome = "Hamburguer";
        String descricao = "Delicioso hambúrguer de carne";
        String categoria = "LANCHE";
        BigDecimal preco = BigDecimal.valueOf(10.00);
        String imagemBase64 = "base64image";

        ProdutoModel produto = ProdutoModel.builder()
                .id(id)
                .nome(nome)
                .descricao(descricao)
                .categoria(categoria)
                .preco(preco)
                .imagemBase64(imagemBase64)
                .build();

        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertEquals(descricao, produto.getDescricao());
        assertEquals(categoria, produto.getCategoria());
        assertEquals(preco, produto.getPreco());
        assertEquals(imagemBase64, produto.getImagemBase64());
    }
}
