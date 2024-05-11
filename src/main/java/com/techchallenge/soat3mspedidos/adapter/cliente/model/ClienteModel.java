package com.techchallenge.soat3mspedidos.adapter.cliente.model;


import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel {

    private UUID id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private LocalDateTime dataHoraCriacao;

    private LocalDateTime dataHoraAlteracao;

    private boolean situacao;

}
