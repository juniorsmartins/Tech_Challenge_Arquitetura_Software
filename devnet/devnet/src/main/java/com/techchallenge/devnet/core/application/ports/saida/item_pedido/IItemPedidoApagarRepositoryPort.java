package com.techchallenge.devnet.core.application.ports.saida.item_pedido;

import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;

public interface IItemPedidoApagarRepositoryPort {

  void deletar(ItemPedidoModel itemPedido);
}

