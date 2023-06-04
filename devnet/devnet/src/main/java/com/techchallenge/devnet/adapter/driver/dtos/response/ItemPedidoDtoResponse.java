package com.techchallenge.devnet.adapter.driver.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResumo;
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

  private ProdutoDtoResumo produto;

  private int quantidade;
}

