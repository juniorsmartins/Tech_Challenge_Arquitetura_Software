package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface IUtilsPedido {
  PedidoModel validarPedido(final PedidoModel pedidoModel);
}

