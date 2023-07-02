package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface ICopaServicePort {

  interface PutService {
    PedidoModel confirmarPedidoPronto(Long idPedido);

    PedidoModel confirmarPedidoFinalizado(Long idPedido);
  }
}

