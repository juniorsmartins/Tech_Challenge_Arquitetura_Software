package com.techchallenge.devnet.core.application.ports.entrada.copa;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface ICopaPedidoProntoServicePort {

  PedidoModel confirmarPedidoPronto(Long idPedido);
}

