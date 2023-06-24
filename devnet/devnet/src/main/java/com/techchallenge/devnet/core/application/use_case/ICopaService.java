package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PedidoDtoResponse;

public interface ICopaService {

  interface AtualizarService {
    PedidoDtoResponse confirmarPedidoPronto(Long idPedido);
  }
}

