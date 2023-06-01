package com.techchallenge.devnet.adapter.driver.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.core.domain.entities.enums.ECategoria;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdutoDtoResponse(

  Long id,

  ECategoria categoria,

  String nome,

  String descricao,

  BigDecimal preco
) { }

