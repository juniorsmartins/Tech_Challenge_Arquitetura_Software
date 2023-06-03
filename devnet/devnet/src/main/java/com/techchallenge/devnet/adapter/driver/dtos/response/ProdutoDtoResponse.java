package com.techchallenge.devnet.adapter.driver.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.core.domain.entities.ItemPedido;
import com.techchallenge.devnet.core.domain.entities.enums.ECategoria;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ProdutoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private ECategoria categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;

  @JsonIgnore
  private Set<ItemPedido> itens;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

