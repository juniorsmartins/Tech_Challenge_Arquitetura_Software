package com.techchallenge.devnet.adapter.driver.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PedidoDtoResponse(

  Long id,

  ClienteDtoResumo cliente,

  Set<ItemDtoResponse> itens
) { }

