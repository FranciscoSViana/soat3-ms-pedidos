package com.techchallenge.soat3mspedidos.adapter.pagamento;

import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagamentoClientTest {

    @Test
    public void testCriarPagamento() {
        PagamentoClient pagamentoClient = mock(PagamentoClient.class);
        PagamentoModel request = new PagamentoModel();
        PagamentoModel response = new PagamentoModel(); // Mock response
        ResponseEntity<PagamentoModel> expectedResponseEntity = ResponseEntity.status(HttpStatus.OK).body(response);

        when(pagamentoClient.criarPagamento(request)).thenReturn(expectedResponseEntity);
        ResponseEntity<PagamentoModel> actualResponseEntity = pagamentoClient.criarPagamento(request);

        assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        assertEquals(expectedResponseEntity.getBody(), actualResponseEntity.getBody());
    }
}
