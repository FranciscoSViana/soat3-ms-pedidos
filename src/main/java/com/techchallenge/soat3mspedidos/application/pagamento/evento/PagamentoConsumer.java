package com.techchallenge.soat3mspedidos.application.pagamento.evento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import com.techchallenge.soat3mspedidos.application.pagamento.service.PagamentoService;
import com.techchallenge.soat3mspedidos.application.pedido.exception.NegocioException;
import com.techchallenge.soat3mspedidos.application.pedido.service.PedidoService;
import com.techchallenge.soat3mspedidos.domain.model.PedidoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.techchallenge.soat3mspedidos.domain.constant.I18n.*;
import static java.lang.String.format;


@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class PagamentoConsumer {

    private final PedidoService pedidoService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = TOPICO_CONFIRMACAO_PAGAMENTO, groupId = GRUPO_ID)
    public void consume(@Payload String pagamentoJson) {
        try {
            log.info("Recebido JSON de pagamento: {}", pagamentoJson);
            String pagamentoFormatado = formataPagamento(pagamentoJson);
            log.info("JSON formatado de pagamento: {}", pagamentoFormatado);
            log.debug("Tentando desserializar para PagamentoModel");
            PagamentoModel pagamento = objectMapper.readValue(pagamentoFormatado, PagamentoModel.class);
            log.debug("Desserialização bem-sucedida: {}", pagamento);
            Optional<PedidoModel> pedido = pedidoService.obterPorUUID(pagamento.getIdPedido());
            pedidoService.confirmarPagamento(pedido.get());
        } catch (Exception e) {
            log.error("Erro ao consumir pagamento: {}, JSON recebido: {}", e.getMessage(), pagamentoJson, e);
            throw new NegocioException(format("Erro ao consumir pagamento: %s", e.getMessage()));
        }
    }

    private static String formataPagamento(String pagamentoJson) {
        return pagamentoJson
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\\n", "")
                .replace("\\t", "")
                .replace("\\r", "")
                .replace("\"{", "{")
                .replace("}\"", "}");
    }
   /* private static String formataPagamento(String pagamentoJson) {
        return pagamentoJson.replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}");
    }

    */
   /* private static String formataPagamento(String pagamentoJson) {
        return pagamentoJson.replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}");
    }

    */
    /*
    private static String formataPagamento(String pagamentoJson) {
        return pagamentoJson.replace("\\\\", "\\")
                .replace("\\\"", "\"")
                .replace("\\n", "")
                .replace("\\t", "")
                .replace("\\r", "");
    }

     */


}
