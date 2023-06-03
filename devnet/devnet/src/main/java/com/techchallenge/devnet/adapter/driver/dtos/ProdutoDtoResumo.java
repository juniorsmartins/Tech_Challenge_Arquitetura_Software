package com.techchallenge.devnet.adapter.driver.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class ProdutoDtoResumo implements Serializable {

  @NotNull
  @Positive
  private Long id;
}

