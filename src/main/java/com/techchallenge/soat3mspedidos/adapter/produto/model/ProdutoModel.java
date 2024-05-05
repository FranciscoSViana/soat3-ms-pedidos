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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoModel {

    private UUID id;

    private TipoCategoria categoria;

    private byte[] imagem;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime dataHoraCriacao;

    private LocalDateTime dataHoraAlteracao;

    private Boolean status;

}
