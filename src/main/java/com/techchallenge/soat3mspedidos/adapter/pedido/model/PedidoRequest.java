package com.techchallenge.soat3mspedidos.adapter.pedido.model;

import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PedidoRequest {
    private UUID id;
    private UUID cliente;
    private List<UUID> produtos;
    private LocalTime tempoPreparo;
}
