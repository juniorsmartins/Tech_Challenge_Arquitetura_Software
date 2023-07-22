package com.techchallenge.devnet.core.application.ports.saida.pedido;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface IPedidoSalvarRepositoryPort {

  PedidoModel salvar(PedidoModel pedidoModel);
}

