package com.techchallenge.soat3mspedidos.application.cliente.service;

import com.techchallenge.soat3mspedidos.adapter.cliente.ClienteClient;
import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.application.pedido.exception.NegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteServiceImpl implements ClienteService {

    private final ClienteClient clienteClient;
    @Override
    public ClienteModel buscarPorCpf(String cpf) {
        return clienteClient.buscarPorCpf(cpf).getBody();
    }

    @Override
    public ClienteModel buscarPorId(UUID id) {
        try {
            return clienteClient.buscarPorId(id).getBody();
        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }


    }


}
