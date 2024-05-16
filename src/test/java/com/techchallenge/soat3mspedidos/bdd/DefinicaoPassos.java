package com.techchallenge.soat3mspedidos.bdd;

import com.techchallenge.soat3mspedidos.adapter.pedido.PedidoController;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.application.pedido.service.PedidoService;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class DefinicaoPassos {

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;

    private PedidoRequest pedidoRequest;
    private PedidoResponse pedidoResponse;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Dado("um novo pedido a ser salvo")
    public void umNovoPedidoASerSalvo() {
        pedidoRequest = PedidoRequest.builder()
                .id(UUID.randomUUID())
                .cliente(UUID.randomUUID())
                .produtos(Collections.singletonList(UUID.randomUUID()))
                .tempoPreparo(LocalTime.now())
                .build();
    }

    @Quando("o pedido é enviado para ser salvo")
    public void oPedidoEEnviadoParaSerSalvo() {
        pedidoResponse = PedidoResponse.builder()
                .id(pedidoRequest.getId())
                .produtos(pedidoRequest.getProdutos())
                .cliente(pedidoRequest.getCliente())
                .statusPedido(StatusPedido.RECEBIDO)
                .preco(BigDecimal.ZERO)
                .statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .tempoPreparo(pedidoRequest.getTempoPreparo())
                .build();

        when(pedidoService.salvar(pedidoRequest)).thenReturn(pedidoResponse);
    }

    @Então("o sistema deve retornar o pedido salvo com sucesso")
    public void oSistemaDeveRetornarOPedidoSalvoComSucesso() {
        ResponseEntity<PedidoResponse> responseEntity = pedidoController.salvarPedido(pedidoRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pedidoResponse, responseEntity.getBody());
    }



}
