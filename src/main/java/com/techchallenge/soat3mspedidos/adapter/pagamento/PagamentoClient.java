package com.techchallenge.soat3mspedidos.adapter.pagamento;

import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "pagamentoClient", url = "${pagamentos.url}")
public interface PagamentoClient {

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<PagamentoModel> criarPagamento(@RequestBody PagamentoModel request);


}
