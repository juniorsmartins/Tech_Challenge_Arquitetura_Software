package com.techchallenge.devnet.enterprise_business_rules.base.utilitarios;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface UtilsEmail {

  PedidoModel notificarPedidoRecebido(PedidoModel pedidoModel);

  PedidoModel notificarPedidoEmPreparacao(PedidoModel pedidoModel);

  PedidoModel notificarPedidoPronto(PedidoModel pedidoModel);

  PedidoModel notificarPedidoFinalizado(PedidoModel pedidoModel);
}

