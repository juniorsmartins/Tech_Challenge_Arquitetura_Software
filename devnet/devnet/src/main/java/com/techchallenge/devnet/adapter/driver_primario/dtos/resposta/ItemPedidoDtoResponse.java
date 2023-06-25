package com.techchallenge.devnet.adapter.driver_primario.dtos.resposta;

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

  @JsonIgnore
  private PedidoDtoResponse pedido;

  private ProdutoDtoResponse produto;

  private int quantidade;
}

