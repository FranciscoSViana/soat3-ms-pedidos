package com.techchallenge.soat3mspedidos.adapter.cliente;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "clienteClient", url = "${clientes.url}")
public interface ClienteClient {

    @GetMapping(value = "/{cpf}", consumes = "application/json", produces = "application/json")
    ResponseEntity<ClienteModel> buscarPorCpf(@PathVariable String cpf);

    @GetMapping(value = "/id/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<ClienteModel> buscarPorId(@PathVariable UUID id);
}
