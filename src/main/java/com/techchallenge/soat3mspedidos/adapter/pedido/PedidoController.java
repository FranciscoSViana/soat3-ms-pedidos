package com.techchallenge.soat3mspedidos.adapter.pedido;

import com.techchallenge.soat3mspedidos.adapter.pedido.model.AlterarStatusPedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoRequest;
import com.techchallenge.soat3mspedidos.adapter.pedido.model.PedidoResponse;
import com.techchallenge.soat3mspedidos.application.pedido.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/pedidos")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> salvarPedido(@RequestBody PedidoRequest request) {

        PedidoResponse pedido = pedidoService.salvar(request);

        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<PedidoResponse> pagarPedido(@PathVariable UUID id) {

        PedidoResponse pedido = pedidoService.pagar(id);

        return ResponseEntity.ok((pedido));
    }

    @PostMapping("/{id}/alterar-status")
    public ResponseEntity<PedidoResponse> alterarStatus(@PathVariable UUID id, @RequestBody AlterarStatusPedidoRequest request) {

        PedidoResponse pedido = pedidoService.alterarStatus(id, request.getStatus());

        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable UUID id) {

        PedidoResponse pedido = pedidoService.buscarPedido(id);

        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> buscarPedidos() {

        List<PedidoResponse> pedido = pedidoService.buscarPedidos();

        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/{id}/criar-pagamento")
    public ResponseEntity<PedidoResponse> criarPagamento(@PathVariable UUID id) {

        PedidoResponse pedido = pedidoService.criarPagamento(id);

        return ResponseEntity.ok(pedido);
    }

}
