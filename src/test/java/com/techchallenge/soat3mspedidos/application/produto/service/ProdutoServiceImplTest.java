package com.techchallenge.soat3mspedidos.application.produto.service;

import com.techchallenge.soat3mspedidos.adapter.produto.ProdutoClient;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import com.techchallenge.soat3mspedidos.application.pedido.exception.NegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceImplTest {

    @Mock
    private ProdutoClient produtoClient;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

     @Test
    void testGetProduto_Success() {
        UUID produtoId = UUID.randomUUID();
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(produtoId);
        ResponseEntity<ProdutoModel> responseEntity = new ResponseEntity<>(produtoModel, HttpStatus.OK);
        when(produtoClient.getProduto(produtoId)).thenReturn(responseEntity);

        ProdutoModel result = produtoService.getProduto(produtoId);

        assertEquals(produtoId, result.getId());
        verify(produtoClient, times(1)).getProduto(produtoId);
    }

    @Test
    void testGetProduto_Exception() {
        UUID produtoId = UUID.randomUUID();
        when(produtoClient.getProduto(produtoId)).thenThrow(new RuntimeException("Erro ao obter produto"));

        assertThrows(NegocioException.class, () -> produtoService.getProduto(produtoId));
        verify(produtoClient, times(1)).getProduto(produtoId);
    }

    @Test
    void testObterProdutosPorIds_Success() {
        List<UUID> produtoIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        List<ProdutoModel> produtos = Arrays.asList(new ProdutoModel(), new ProdutoModel());
        ResponseEntity<List<ProdutoModel>> responseEntity = new ResponseEntity<>(produtos, HttpStatus.OK);
        when(produtoClient.obterProdutosPorIDs(anyList())).thenReturn(responseEntity);

        List<ProdutoModel> result = produtoService.obterProdutosPorIds(produtoIds);

        assertEquals(produtos.size(), result.size());
        verify(produtoClient, times(1)).obterProdutosPorIDs(produtoIds);
    }

    @Test
    void testObterProdutosPorIds_Exception() {
        List<UUID> produtoIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        when(produtoClient.obterProdutosPorIDs(anyList())).thenThrow(new RuntimeException("Erro ao obter produtos"));

        assertThrows(NegocioException.class, () -> produtoService.obterProdutosPorIds(produtoIds));
        verify(produtoClient, times(1)).obterProdutosPorIDs(produtoIds);
    }
}
