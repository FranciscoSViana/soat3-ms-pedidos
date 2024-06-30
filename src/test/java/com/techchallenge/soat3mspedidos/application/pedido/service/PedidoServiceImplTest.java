package com.techchallenge.soat3mspedidos.application.pedido.service;

import com.techchallenge.soat3mspedidos.adapter.cliente.model.ClienteModel;
import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.adapter.produto.model.ProdutoModel;
import com.techchallenge.soat3mspedidos.application.cliente.service.ClienteService;
import com.techchallenge.soat3mspedidos.application.pagamento.evento.PagamentoProducer;
import com.techchallenge.soat3mspedidos.application.pagamento.service.PagamentoService;
import com.techchallenge.soat3mspedidos.application.pedido.converter.PedidoMapper;
import com.techchallenge.soat3mspedidos.application.produto.service.ProdutoService;
import com.techchallenge.soat3mspedidos.domain.model.PedidoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import com.techchallenge.soat3mspedidos.infrastruture.pedido.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private PagamentoService pagamentoService;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private PedidoRequest pedidoRequest;

    private PedidoModel pedidoModel;

    private PedidoResponse pedidoResponse;

    private List<UUID> produtoIds;

    private ProdutoModel produtoModel;

    private List<ProdutoModel> produtosModel;

    private ClienteModel clienteModel;
    @Mock
    private PagamentoProducer producer;

    @BeforeEach
    public void setUp() {
        pedidoRequest = new PedidoRequest();
        pedidoRequest.setId(UUID.randomUUID());

        List<UUID> produtos = new ArrayList<>();
        pedidoRequest.setProdutos(produtos);
        pedidoRequest.setTempoPreparo(LocalTime.now());
        pedidoRequest.setCliente(UUID.randomUUID());

        pedidoModel = new PedidoModel();
        clienteModel = new ClienteModel();
        clienteModel.setId(UUID.randomUUID());
        pedidoModel.setCliente(clienteModel);
        pedidoModel.setStatusPedido(StatusPedido.RECEBIDO);

        produtosModel = new ArrayList<>();
        produtoModel = new ProdutoModel();
        produtoModel.setId(UUID.randomUUID());
        produtosModel.add(produtoModel);
        pedidoModel.setProdutos(produtosModel);

        pedidoResponse = new PedidoResponse();
        pedidoResponse.setCliente(UUID.randomUUID());
        pedidoResponse.setId(UUID.randomUUID());

        produtoIds = new ArrayList<>();
        produtoIds.add(produtoModel.getId());
    }

    @Test
    public void salvarTest() {
        when(pedidoMapper.pedidoRequestToPedidoModel(pedidoRequest)).thenReturn(pedidoModel);
        when(pedidoMapper.pedidoToPedidoRespose(pedidoModel)).thenReturn(pedidoResponse);
        when(produtoService.obterProdutosPorIds(produtoIds)).thenReturn(produtosModel);
        when(clienteService.buscarPorId(pedidoModel.getCliente().getId())).thenReturn(clienteModel);

        PedidoResponse response = pedidoService.salvar(pedidoRequest);

        verify(pedidoRepository).save(pedidoModel);
        verify(pedidoMapper).pedidoRequestToPedidoModel(pedidoRequest);
        verify(pedidoMapper).pedidoToPedidoRespose(pedidoModel);
    }

    @Test
    void testPagar() {
        UUID pedidoId = UUID.randomUUID();
        PedidoModel pedido = new PedidoModel();
        pedido.setId(pedidoId);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);

        when(pedidoRepository.findById(pedidoId)).thenReturn(java.util.Optional.of(pedido));

        PedidoResponse response = pedidoService.pagar(pedidoId);

        verify(pedidoRepository).findById(pedidoId);
        verify(pedidoRepository).save(pedido);
        verify(pedidoMapper).pedidoToPedidoRespose(pedido);
    }

    @Test
    void testAlterarStatus() {
        UUID pedidoId = UUID.randomUUID();
        PedidoModel pedido = new PedidoModel();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);

        when(pedidoRepository.findById(pedidoId)).thenReturn(java.util.Optional.of(pedido));

        PedidoResponse response = pedidoService.alterarStatus(pedidoId, StatusPedido.EM_PREPARACAO);

        verify(pedidoRepository).findById(pedidoId);
        verify(pedidoRepository).save(pedido);
        verify(pedidoMapper).pedidoToPedidoRespose(pedido);
    }

    @Test
    void testBuscarPedido() {
        when(pedidoRepository.findById(pedidoModel.getId())).thenReturn(Optional.of(pedidoModel));

        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setId(pedidoModel.getId());

        when(pedidoMapper.pedidoToPedidoRespose(pedidoModel)).thenReturn(pedidoResponse);

        PedidoResponse response = pedidoService.buscarPedido(pedidoModel.getId());

        verify(pedidoRepository).findById(pedidoModel.getId());
        verify(pedidoMapper).pedidoToPedidoRespose(pedidoModel);
        assertEquals(pedidoModel.getId(), response.getId());
    }

    @Test
    void testBuscarPedidos() {
        List<PedidoModel> pedidoModels = Arrays.asList(pedidoModel);
        List<PedidoResponse> pedidoResponses = Arrays.asList(pedidoResponse);

        when(pedidoRepository.findAll()).thenReturn(pedidoModels);

        when(pedidoMapper.pedidosToPedidosRespose(pedidoModels)).thenReturn(pedidoResponses);

        List<PedidoResponse> responses = pedidoService.buscarPedidos();

        verify(pedidoRepository).findAll();
        verify(pedidoMapper).pedidosToPedidosRespose(pedidoModels);
        assertEquals(pedidoResponses, responses);
    }

    @Test
    void testCriarPagamento() {
        UUID pedidoId = UUID.randomUUID();

        PedidoModel pedido = new PedidoModel();
        pedidoModel.setId(pedidoId);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setId(UUID.randomUUID());
        pagamento.setStatusPagamento(StatusPagamento.PAGO);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));
        when(pedidoMapper.convertPedidoToPagamento(pedido)).thenReturn(pagamento);
        when(pedidoMapper.convertPagamentoToPedido(pagamento)).thenReturn(pedido);


        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setId(UUID.randomUUID());

        when(pedidoMapper.pedidoToPedidoRespose(pedido)).thenReturn(pedidoResponse);

        PedidoResponse response = pedidoService.criarPagamento(pedidoId);

        verify(pedidoRepository).findById(pedidoId);
        verify(pedidoMapper).convertPedidoToPagamento(pedido);
        verify(pedidoRepository).save(pedido);
        verify(pedidoMapper).pedidoToPedidoRespose(pedido);
    }


    @Test
    void testObterPorUUID() {
        UUID pedidoId = UUID.randomUUID();

        PedidoModel pedido = new PedidoModel();
        pedido.setId(UUID.randomUUID());
        pedido.setStatusPedido(StatusPedido.RECEBIDO);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        Optional<PedidoModel> result = pedidoService.obterPorUUID(pedidoId);

        verify(pedidoRepository).findById(pedidoId);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(pedido, result.get());
    }

    @Test
    void testObterPedidosComPagamentoAguardando() {
        List<PedidoModel> pedidosAguardandoPagamento = new ArrayList<>();
        PedidoModel pedido1 = PedidoModel.builder().id(UUID.randomUUID()).statusPedido(StatusPedido.RECEBIDO).statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO).build();
        PedidoModel pedido2 = PedidoModel.builder().id(UUID.randomUUID()).statusPedido(StatusPedido.RECEBIDO).statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO).build();
        pedidosAguardandoPagamento.add(pedido1);
        pedidosAguardandoPagamento.add(pedido2);

        when(pedidoRepository.findByStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO)).thenReturn(pedidosAguardandoPagamento);

        List<PedidoModel> result = pedidoService.obterPedidosComPagamentoAguardando();

        verify(pedidoRepository).findByStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);
        assertNotNull(result);
        assertEquals(pedidosAguardandoPagamento.size(), result.size());
        assertEquals(pedidosAguardandoPagamento, result);
    }

    @Test
    void testObterPorIdPagamentoMP() {
        String idPagamento = "pagamento123";

        PedidoModel pedido = PedidoModel.builder().id(UUID.randomUUID()).idPagamentoMP(idPagamento).build();

        when(pedidoRepository.findByIdPagamentoMP(idPagamento)).thenReturn(pedido);

        PedidoModel result = pedidoService.obterPorIdPagamentoMP(idPagamento);

        verify(pedidoRepository).findByIdPagamentoMP(idPagamento);
        assertNotNull(result);
        assertEquals(pedido, result);
    }

    @Test
    void testConfirmarPagamento() {
        PedidoModel pedido = PedidoModel.builder().id(UUID.randomUUID()).statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO).build();

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        PedidoModel result = pedidoService.confirmarPagamento(pedido);

        verify(pedidoRepository).save(pedido);
        assertEquals(StatusPagamento.PAGO, result.getStatusPagamento());
    }

    @Test
    void testSalvar() {
        PedidoModel pedido = PedidoModel.builder().id(UUID.randomUUID()).statusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO).build();

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        PedidoModel result = pedidoService.salvar(pedido);

        verify(pedidoRepository).save(pedido);
        assertEquals(pedido, result);
    }


}
