package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.domain.models.Pedido;

public interface IUtils {

  Pedido confirmarCliente(Pedido pedido);

  Pedido confirmarProdutos(Pedido pedido);

  Pedido notificarPedidoRecebido(Pedido pedido);

  Pedido notificarPedidoEmPreparacao(Pedido pedido);

  Pedido notificarPedidoPronto(Pedido pedido);

  Pedido notificarPedidoFinalizado(Pedido pedido);
}

