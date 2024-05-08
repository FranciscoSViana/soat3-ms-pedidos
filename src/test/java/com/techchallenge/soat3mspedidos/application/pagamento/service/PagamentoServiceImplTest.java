package com.techchallenge.soat3mspedidos.application.pagamento.service;

import com.techchallenge.soat3mspedidos.adapter.pagamento.PagamentoClient;
import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceImplTest {

    @Mock
    private PagamentoClient pagamentoClient;

    @InjectMocks
    private PagamentoServiceImpl pagamentoService;

    @Test
    public void testCriarPagamento() {
        PagamentoModel request = new PagamentoModel();
        request.setId(UUID.randomUUID());

        PagamentoModel response = new PagamentoModel();
        response.setId(request.getId());

        when(pagamentoClient.criarPagamento(request)).thenReturn(mock(ResponseEntity.class));
        when(pagamentoClient.criarPagamento(request).getBody()).thenReturn(response);

        PagamentoModel pagamentoCriado = pagamentoService.criarPagamento(request);

        assertEquals(response.getId(), pagamentoCriado.getId());
        assertEquals(response.getIdPagamentoMP(), pagamentoCriado.getIdPagamentoMP());
    }
}
