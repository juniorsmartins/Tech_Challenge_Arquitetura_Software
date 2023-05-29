package com.techchallenge.devnet.adapter.driver.dtos;

import com.techchallenge.devnet.core.domain.entities.enums.ECategoria;

import java.math.BigDecimal;

public record ProdutoDtoResponse(

  Long id,

  ECategoria categoria,

  String nome,

  String descricao,

  BigDecimal preco
) { }

