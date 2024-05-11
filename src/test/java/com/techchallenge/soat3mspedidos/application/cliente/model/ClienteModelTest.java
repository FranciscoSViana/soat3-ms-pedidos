package com.techchallenge.soat3mspedidos.application.cliente.model;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteModelTest {

    @Test
    public void testClienteModel() {
        UUID id = UUID.randomUUID();
        String nome = "João";
        String cpf = "123.456.789-00";
        String email = "joao@example.com";
        String telefone = "123456789";
        LocalDateTime dataHoraCriacao = LocalDateTime.now();
        LocalDateTime dataHoraAlteracao = LocalDateTime.now();
        boolean situacao = true;

        ClienteModel cliente = ClienteModel.builder().id(id).nome(nome).cpf(cpf).email(email).telefone(telefone).dataHoraCriacao(dataHoraCriacao).dataHoraAlteracao(dataHoraAlteracao).situacao(situacao).build();

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(email, cliente.getEmail());
        assertEquals(telefone, cliente.getTelefone());
        assertEquals(dataHoraCriacao, cliente.getDataHoraCriacao());
        assertEquals(dataHoraAlteracao, cliente.getDataHoraAlteracao());
        assertTrue(cliente.isSituacao());
    }
}
