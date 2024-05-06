package com.techchallenge.soat3mspedidos.adapter.produto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techchallenge.soat3mspedidos.domain.model.enumerate.TipoCategoria;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {

    private UUID id;
    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private String imagemBase64;

}
