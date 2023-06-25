package com.techchallenge.devnet.adapter.driver_primario.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.core.domain.entities.enums.CategoriaEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ProdutoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private CategoriaEnum categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

