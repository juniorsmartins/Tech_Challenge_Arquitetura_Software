package com.techchallenge.devnet.adapter.driver.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record PedidoDtoRequest(

  @Valid
  ClienteDtoResumo cliente,

  @NotNull
  @Valid
  Set<ItemDtoRequest> itens
) { }

