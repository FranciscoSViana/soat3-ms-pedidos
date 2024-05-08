package com.techchallenge.soat3mspedidos.application.pedido.converter;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import com.techchallenge.soat3mspedidos.application.cliente.service.ClienteService;
import com.techchallenge.soat3mspedidos.application.produto.service.ProdutoService;
import com.techchallenge.soat3mspedidos.domain.model.PedidoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoMapperTest {

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private PedidoMapper pedidoMapper;

    private UUID clienteUUID;
    private UUID produtoUUID;
    private UUID pedidoUUID;

    private ClienteModel createMockClienteModel(UUID id) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(id);
        clienteModel.setNome("Teste");
        clienteModel.setCpf("12345678900");
        return clienteModel;
    }

    private ProdutoModel createMockProdutoModel(UUID id) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setId(id);
        produtoModel.setNome("Produto de Teste");
        produtoModel.setPreco(BigDecimal.valueOf(10.0));
        return produtoModel;
    }

    @BeforeEach
    public void setup() {
        clienteUUID = UUID.randomUUID();
        produtoUUID = UUID.randomUUID();
        pedidoUUID = UUID.randomUUID();
    }

    @Test
    void testConvertPedidoToPagamento() {
        PedidoModel pedidoModel = PedidoModel.builder()
                .cliente(createMockClienteModel(clienteUUID))
                .statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .id(pedidoUUID)
                .codigoPix("PIX123")
                .build();

        PagamentoModel pagamentoModel = pedidoMapper.convertPedidoToPagamento(pedidoModel);

        assertEquals(pedidoModel.getCliente(), pagamentoModel.getCliente());
        assertEquals(pedidoModel.getStatusPagamento(), pagamentoModel.getStatusPagamento());
        assertEquals(pedidoModel.getId(), pagamentoModel.getId());
        assertEquals(pedidoModel.getPreco(), pagamentoModel.getPreco());
        assertEquals(pedidoModel.getCodigoPix(), pagamentoModel.getCodigoPix());
    }

    @Test
    void testConvertPagamentoToPedido() {
        PagamentoModel pagamentoModel = PagamentoModel.builder()
                .idPagamentoMP("MP123")
                .cliente(this.createMockClienteModel(clienteUUID))
                .codigoPix("PIX123")
                .statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .build();

        PedidoModel pedidoModel = pedidoMapper.convertPagamentoToPedido(pagamentoModel);

        assertEquals(StatusPedido.RECEBIDO, pedidoModel.getStatusPedido());
        assertEquals(pagamentoModel.getCliente(), pedidoModel.getCliente());
        assertEquals(pagamentoModel.getCodigoPix(), pedidoModel.getCodigoPix());
        assertEquals(pagamentoModel.getStatusPagamento(), pedidoModel.getStatusPagamento());
        assertEquals(pagamentoModel.getQrCode(), pedidoModel.getQrCode());
    }

    @Test
    public void testPedidoRequestToPedidoModel() {
        when(clienteService.buscarPorId(clienteUUID)).thenReturn(createMockClienteModel(clienteUUID));
        when(produtoService.getProduto(produtoUUID)).thenReturn(createMockProdutoModel(produtoUUID));

        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setCliente(clienteUUID);
        pedidoRequest.setTempoPreparo(LocalTime.now());
        List<UUID> produtos = new ArrayList<>();
        produtos.add(produtoUUID);
        pedidoRequest.setProdutos(produtos);

        PedidoModel pedidoModel = pedidoMapper.pedidoRequestToPedidoModel(pedidoRequest);

        assertEquals(StatusPedido.RECEBIDO, pedidoModel.getStatusPedido());
        assertEquals(StatusPagamento.AGUARDANDO_PAGAMENTO, pedidoModel.getStatusPagamento());
        assertEquals(1, pedidoModel.getProdutos().size());
        assertEquals(produtoUUID, pedidoModel.getProdutos().get(0).getId());
    }

    @Test
    public void testPedidosToPedidosRespose() {
        List<ProdutoModel> produtos = new ArrayList<>();
        ProdutoModel mockProdutoModel = this.createMockProdutoModel(UUID.randomUUID());
        produtos.add(mockProdutoModel);

        List<PedidoModel> pedidos = new ArrayList<>();
        PedidoModel pedido1 = new PedidoModel();
        pedido1.setId(UUID.randomUUID());
        pedido1.setCliente(createMockClienteModel(clienteUUID));
        pedido1.setPreco(BigDecimal.valueOf(50.0));
        pedido1.setProdutos(produtos);
        pedidos.add(pedido1);

        PedidoModel pedido2 = new PedidoModel();
        pedido2.setId(UUID.randomUUID());
        pedido2.setCliente(createMockClienteModel(clienteUUID));
        pedido2.setPreco(BigDecimal.valueOf(75.0));
        pedido2.setProdutos(produtos);
        pedidos.add(pedido2);

        List<PedidoResponse> pedidoResponses = pedidoMapper.pedidosToPedidosRespose(pedidos);

        assertEquals(2, pedidoResponses.size());
        assertEquals(pedido1.getId(), pedidoResponses.get(0).getId());
        assertEquals(pedido1.getCliente().getId(), pedidoResponses.get(0).getCliente());
        assertEquals(pedido1.getPreco(), pedidoResponses.get(0).getPreco());
        assertEquals(pedido1.getStatusPedido(), pedidoResponses.get(0).getStatusPedido());
        assertEquals(pedido1.getStatusPagamento(), pedidoResponses.get(0).getStatusPagamento());
        assertEquals(pedido2.getId(), pedidoResponses.get(1).getId());
        assertEquals(pedido2.getCliente().getId(), pedidoResponses.get(1).getCliente());
        assertEquals(pedido2.getPreco(), pedidoResponses.get(1).getPreco());
        assertEquals(pedido2.getStatusPedido(), pedidoResponses.get(1).getStatusPedido());
        assertEquals(pedido2.getStatusPagamento(), pedidoResponses.get(1).getStatusPagamento());
    }
}