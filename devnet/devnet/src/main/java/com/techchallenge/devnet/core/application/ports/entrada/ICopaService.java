package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;

public interface ICopaService {

  interface PutService {
    PedidoDtoResponse confirmarPedidoPronto(Long idPedido);

    PedidoDtoResponse confirmarPedidoFinalizado(Long idPedido);
  }
}

