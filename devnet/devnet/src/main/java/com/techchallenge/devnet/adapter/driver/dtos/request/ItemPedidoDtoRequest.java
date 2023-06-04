package com.techchallenge.devnet.adapter.driver.dtos.request;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResumo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class ItemPedidoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Valid
  private ProdutoDtoResumo produto;

  @NotNull
  @Positive
  private int quantidade;
}

