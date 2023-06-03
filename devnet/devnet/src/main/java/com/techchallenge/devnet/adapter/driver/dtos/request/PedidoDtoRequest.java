package com.techchallenge.devnet.adapter.driver.dtos.request;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResumo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public final class PedidoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Valid
  private ClienteDtoResumo cliente;

  @NotNull
  @Size(min = 1)
  @Valid
  private Set<ItemPedidoDtoRequest> itensPedido;
}

