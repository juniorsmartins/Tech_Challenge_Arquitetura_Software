package com.techchallenge.devnet.core.domain.base.utils;

import com.techchallenge.devnet.core.domain.entities.Pedido;

public interface IUtils {

  Pedido confirmarCliente(Pedido pedido);

  Pedido confirmarProdutos(Pedido pedido);
}

