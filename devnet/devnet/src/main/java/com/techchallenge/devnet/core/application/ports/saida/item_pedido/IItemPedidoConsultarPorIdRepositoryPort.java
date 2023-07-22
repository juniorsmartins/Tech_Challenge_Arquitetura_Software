package com.techchallenge.devnet.core.application.ports.saida.item_pedido;

import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;

import java.util.Optional;

public interface IItemPedidoConsultarPorIdRepositoryPort {

  Optional<ItemPedidoModel> consultarPorId(Long id);
}

