package com.techchallenge.soat3mspedidos.application.pagamento.service;

import com.techchallenge.soat3mspedidos.adapter.pagamento.PagamentoClient;
import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PagamentoServiceImpl implements PagamentoService{

    private final PagamentoClient pagamentoClient;

    @Override
    public PagamentoModel criarPagamento(PagamentoModel request) {
        return pagamentoClient.criarPagamento(request).getBody();
    }
}
