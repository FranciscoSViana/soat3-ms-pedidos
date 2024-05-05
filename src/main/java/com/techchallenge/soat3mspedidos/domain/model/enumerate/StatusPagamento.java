package com.techchallenge.soat3mspedidos.domain.model.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.String.format;
import static java.util.Arrays.stream;

@Getter
@AllArgsConstructor
public enum StatusPagamento {
    PAGO("Pago"),
    AGUARDANDO_PAGAMENTO("aguardandoPagamento");

    private String descricao;
    public static StatusPagamento fromName(String name) {
        return stream(values())
                .filter(value -> value.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("Categoria inválida: {}", name)));

    }
}
