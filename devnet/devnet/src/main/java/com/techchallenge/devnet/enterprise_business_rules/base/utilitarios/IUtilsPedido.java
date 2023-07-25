package com.techchallenge.devnet.enterprise_business_rules.base.utilitarios;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface IUtilsPedido {
  PedidoModel validarPedido(final PedidoModel pedidoModel);
}

