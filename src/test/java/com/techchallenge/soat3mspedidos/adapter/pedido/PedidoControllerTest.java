package com.techchallenge.soat3mspedidos.adapter.pedido;

import com.techchallenge.soat3mspedidos.adapter.pedido.model.AlterarStatusPedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.application.pedido.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @InjectMocks
    private PedidoController controller;
    @Mock
    private PedidoService pedidoService;

    @Test
    void testSalvarPedido() {
        PedidoRequest request = new PedidoRequest();
        PedidoResponse expectedResponse = new PedidoResponse();
        when(pedidoService.salvar(request)).thenReturn(expectedResponse);

        ResponseEntity<PedidoResponse> response = controller.salvarPedido(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testPagarPedido() {
        UUID id = UUID.randomUUID();
        PedidoResponse expectedResponse = new PedidoResponse();
        when(pedidoService.pagar(id)).thenReturn(expectedResponse);

        ResponseEntity<PedidoResponse> response = controller.pagarPedido(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testAlterarStatus() {
        UUID id = UUID.randomUUID();
        AlterarStatusPedidoRequest request = new AlterarStatusPedidoRequest();
        PedidoResponse expectedResponse = new PedidoResponse();
        when(pedidoService.alterarStatus(id, request.getStatus())).thenReturn(expectedResponse);

        ResponseEntity<PedidoResponse> response = controller.alterarStatus(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testBuscarPedido() {
        UUID id = UUID.randomUUID();
        PedidoResponse expectedResponse = new PedidoResponse();
        when(pedidoService.buscarPedido(id)).thenReturn(expectedResponse);

        ResponseEntity<PedidoResponse> response = controller.buscarPedido(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testBuscarPedidos() {
        List<PedidoResponse> expectedResponse = Collections.singletonList(new PedidoResponse());
        when(pedidoService.buscarPedidos()).thenReturn(expectedResponse);

        ResponseEntity<List<PedidoResponse>> response = controller.buscarPedidos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testCriarPagamento() {
        UUID id = UUID.randomUUID();
        PedidoResponse expectedResponse = new PedidoResponse();
        when(pedidoService.criarPagamento(id)).thenReturn(expectedResponse);

        ResponseEntity<PedidoResponse> response = controller.criarPagamento(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}
