package com.techchallenge.soat3mspedidos.adapter.pedido.model;


import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlterarStatusPedidoRequest {
    StatusPedido status;
}
