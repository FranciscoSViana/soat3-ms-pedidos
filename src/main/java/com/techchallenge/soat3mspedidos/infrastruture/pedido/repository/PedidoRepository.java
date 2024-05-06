package com.techchallenge.soat3mspedidos.infrastruture.pedido.repository;


import com.techchallenge.soat3mspedidos.domain.model.PedidoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends MongoRepository<PedidoModel, UUID> {
    PedidoModel findByIdPagamentoMP(String idPagamento);


    List<PedidoModel> findByStatusPagamento(StatusPagamento statusPagamento);


}


