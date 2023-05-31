package com.techchallenge.devnet.adapter.driver.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoDtoResumo(

  @NotNull
  @Positive
  Long id
) { }

