package com.techchallenge.devnet.core.application.ports.entrada.copa;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface ICopaPedidoFinalizadoServicePort {

  PedidoModel confirmarPedidoFinalizado(Long idPedido);
}

