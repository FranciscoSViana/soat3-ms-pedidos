package com.techchallenge.soat3mspedidos.application.cliente.service;

import com.techchallenge.soat3mspedidos.adapter.cliente.ClienteClient;
import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteClient clienteClient;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    public void testBuscarPorCpf() {
        String cpf = "12345678900";
        ClienteModel clienteModelMock = new ClienteModel();
        clienteModelMock.setId(UUID.randomUUID());
        clienteModelMock.setNome("Teste");
        clienteModelMock.setCpf(cpf);

        when(clienteClient.buscarPorCpf(cpf)).thenReturn(ResponseEntity.ok(clienteModelMock));

        ClienteModel clienteModelRetornado = clienteService.buscarPorCpf(cpf);

        assertEquals(clienteModelMock, clienteModelRetornado);
    }

    @Test
    public void testBuscarPorId() {
        UUID id = UUID.randomUUID();
        ClienteModel clienteModelMock = new ClienteModel();
        clienteModelMock.setId(id);
        clienteModelMock.setNome("Teste");
        clienteModelMock.setCpf("12345678900");

        when(clienteClient.buscarPorId(id)).thenReturn(ResponseEntity.ok(clienteModelMock));

        ClienteModel clienteModelRetornado = clienteService.buscarPorId(id);

        assertEquals(clienteModelMock, clienteModelRetornado);
    }
}
