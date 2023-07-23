package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface IUtilsEmail {

  PedidoModel notificarPedidoRecebido(PedidoModel pedidoModel);

  PedidoModel notificarPedidoEmPreparacao(PedidoModel pedidoModel);

  PedidoModel notificarPedidoPronto(PedidoModel pedidoModel);

  PedidoModel notificarPedidoFinalizado(PedidoModel pedidoModel);
}

