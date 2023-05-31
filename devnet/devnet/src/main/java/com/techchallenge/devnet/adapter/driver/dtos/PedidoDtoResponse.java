package com.techchallenge.devnet.adapter.driver.dtos;

import java.util.Set;

public record PedidoDtoResponse(

  ClienteDtoResumo cliente,

  Set<ItemDtoResponse> itens
) { }

