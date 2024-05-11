package com.techchallenge.soat3mspedidos.adapter.pedido.model;


import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlterarStatusPedidoRequest {
    StatusPedido status;
}
