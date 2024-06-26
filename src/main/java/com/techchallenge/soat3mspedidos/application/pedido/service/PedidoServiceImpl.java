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
import com.techchallenge.soat3mspedidos.application.pedido.exception.NegocioException;
import com.techchallenge.soat3mspedidos.application.produto.service.ProdutoService;
import com.techchallenge.soat3mspedidos.domain.constant.I18n;
import com.techchallenge.soat3mspedidos.domain.model.PedidoModel;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPagamento;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.StatusPedido;
import com.techchallenge.soat3mspedidos.infrastruture.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    private final ClienteService clienteService;

    private final ProdutoService produtoService;

    private final PagamentoProducer producer;

    private final PedidoMapper pedidoMapper;


    @Override
    public PedidoResponse salvar(PedidoRequest request) {

        PedidoModel salvarPedido = pedidoMapper.pedidoRequestToPedidoModel(request);

        List<ProdutoModel> produtos = buscarProdutosPorIdOuLancarErro(salvarPedido);

        salvarPedido.setPreco(calcularValorTotalComStreams(produtos));

        ClienteModel cliente = buscarClientePorIdOuLancarErro(salvarPedido);

        validarCriacaoPedido(salvarPedido, cliente);

        repository.save(salvarPedido);

        return pedidoMapper.pedidoToPedidoRespose(salvarPedido);
    }


    @Override
    public PedidoResponse pagar(UUID id) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        pedido.setStatusPagamento(StatusPagamento.PAGO);

        repository.save(pedido);

        return pedidoMapper.pedidoToPedidoRespose(pedido);
    }

    @Override
    public PedidoResponse alterarStatus(UUID id, StatusPedido status) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        if (pedido.getStatusPedido() != null) {

            pedido.setStatusPedido(status);

        }

        repository.save(pedido);

        return pedidoMapper.pedidoToPedidoRespose(pedido);
    }

    @Override
    public PedidoResponse buscarPedido(UUID id) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        return pedidoMapper.pedidoToPedidoRespose(pedido);
    }

    @Override
    public List<PedidoResponse> buscarPedidos() {

        List<PedidoModel> pedidos = repository.findAll();

        return pedidoMapper.pedidosToPedidosRespose(pedidos);
    }

    @Override
    public PedidoResponse criarPagamento(UUID id) {

        PedidoModel pedido = encontrarPedidoPorIdOuLancarErro(id);

        PagamentoModel pagamento = pedidoMapper.convertPedidoToPagamento(pedido);

        producer.solicitarPagamento(pagamento);

        // observar este ponto
        PedidoModel pedidoConvertido = pedidoMapper.convertPagamentoToPedido(pagamento);

        pedidoConvertido.setId(pedido.getId());

        pedidoConvertido.setProdutos(pedido.getProdutos());

        pedidoConvertido.setPreco(pedido.getPreco());

        repository.save(pedidoConvertido);

        return pedidoMapper.pedidoToPedidoRespose(pedidoConvertido);
    }

    @Override
    public Optional<PedidoModel> obterPorUUID(UUID idPagamento) {

        return repository.findById(idPagamento);
    }

    @Override
    public List<PedidoModel> obterPedidosComPagamentoAguardando() {
        StatusPagamento aguardandoPagamento = StatusPagamento.AGUARDANDO_PAGAMENTO;
        return repository.findByStatusPagamento(aguardandoPagamento);
    }

    @Override
    public PedidoModel obterPorIdPagamentoMP(String idPagamento) {
        return repository.findByIdPagamentoMP(idPagamento);
    }

    @Override
    public PedidoModel confirmarPagamento(PedidoModel pedido) {

        pedido.setStatusPagamento(StatusPagamento.PAGO);

        return repository.save(pedido);

    }

    @Override
    public PedidoModel salvar(PedidoModel pedido) {
        return repository.save(pedido);
    }


    private BigDecimal calcularValorTotalComStreams(List<ProdutoModel> produtos) {
        return produtos.stream()
                .filter(produto -> produto != null && produto.getPreco() != null)
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ProdutoModel> buscarProdutosPorIdOuLancarErro(PedidoModel pedido) {

        List<UUID> produtoIds = pedido.getProdutos().stream().map(ProdutoModel::getId).toList();

        List<ProdutoModel> produtos = produtoService.obterProdutosPorIds(produtoIds);

        if (produtos.isEmpty()) {
            throw new NegocioException(I18n.SEM_PRODUTOS_VALIDOS);
        }

        return produtos;
    }

    private ClienteModel buscarClientePorIdOuLancarErro(PedidoModel pedido) {
        return clienteService.buscarPorId(pedido.getCliente().getId());
    }

    private PedidoModel encontrarPedidoPorIdOuLancarErro(UUID id) {

        Optional<PedidoModel> pedido =  repository.findById(id);

        if (pedido.isPresent()) {
            return pedido.get();
        } else {
            throw new NegocioException("Pedido não encontrado para o ID: " + id);
        }
    }

    private void validarCriacaoPedido(PedidoModel pedido, ClienteModel cliente) {
        if (!cliente.getId().equals(pedido.getCliente().getId())) {
            throw new NegocioException(I18n.CLIENTE_INVALIDO);
        }
        if (pedido.getStatusPedido() != StatusPedido.RECEBIDO) {
            throw new NegocioException(I18n.NAO_E_POSSIVEL_ADICIONAR_ITENS_AO_PEDIDO);
        }
    }


}

