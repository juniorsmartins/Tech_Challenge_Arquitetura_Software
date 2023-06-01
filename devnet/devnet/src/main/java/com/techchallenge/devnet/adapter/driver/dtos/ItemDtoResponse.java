package com.techchallenge.devnet.adapter.driver.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ItemDtoResponse(

  Long id,

  ProdutoDtoResponse produto,

  int quantidade
) { }

