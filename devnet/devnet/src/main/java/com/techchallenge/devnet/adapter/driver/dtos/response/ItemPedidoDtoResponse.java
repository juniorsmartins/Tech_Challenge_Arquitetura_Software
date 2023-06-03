package com.techchallenge.devnet.adapter.driver.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ItemPedidoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long pedidoId;

  private Long produtoId;

  @JsonIgnore
  private PedidoDtoResponse pedido;

  //  @JsonIgnore
  private ProdutoDtoResponse produto;

  private int quantidade;
}

