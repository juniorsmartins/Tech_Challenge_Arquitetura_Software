package com.techchallenge.devnet.adapter.driver.dtos;

import com.techchallenge.devnet.core.domain.entities.enums.ECategoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record ProdutoDtoRequest(

  @NotBlank
  @Enumerated(EnumType.STRING)
  ECategoria categoria,

  @NotBlank
  @Length(max = 100)
  String nome,

  @NotBlank
  @Length(max = 250)
  String descricao,

  @NotNull
  @PositiveOrZero
  BigDecimal preco
) { }

