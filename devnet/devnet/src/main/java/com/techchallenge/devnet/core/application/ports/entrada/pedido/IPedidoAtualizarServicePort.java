package com.techchallenge.devnet.core.application.ports.entrada.pedido;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface IPedidoAtualizarServicePort {

  PedidoModel atualizar(Long id, PedidoModel pedidoModel);
}

