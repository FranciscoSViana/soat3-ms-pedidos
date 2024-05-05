package com.techchallenge.soat3mspedidos.application.cliente.service;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;

import java.util.UUID;

public interface ClienteService {
    ClienteModel buscarPorCpf(String cpf);

    ClienteModel buscarPorId(UUID id);

}
