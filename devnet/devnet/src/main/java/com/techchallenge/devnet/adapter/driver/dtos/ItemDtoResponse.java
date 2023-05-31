package com.techchallenge.devnet.adapter.driver.dtos;

public record ItemDtoResponse(

  Long id,

  ProdutoDtoResponse produto,

  int quantidade
) { }

