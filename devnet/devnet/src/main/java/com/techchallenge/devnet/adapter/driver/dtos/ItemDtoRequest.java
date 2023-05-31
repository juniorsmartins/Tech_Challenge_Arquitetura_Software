package com.techchallenge.devnet.adapter.driver.dtos;

import com.techchallenge.devnet.core.domain.entities.Produto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemDtoRequest(

  @NotNull
  @Valid
  ProdutoDtoResumo produto,

  @NotNull
  @Positive
  int quantidade
) { }

