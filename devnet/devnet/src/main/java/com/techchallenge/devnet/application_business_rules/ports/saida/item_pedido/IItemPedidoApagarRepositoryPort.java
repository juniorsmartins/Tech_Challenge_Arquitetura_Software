package com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;

public interface IItemPedidoApagarRepositoryPort {

  void deletar(ItemPedidoModel itemPedido);
}

