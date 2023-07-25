package com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;

import java.util.Optional;

public interface IItemPedidoConsultarPorIdRepositoryPort {

  Optional<ItemPedidoModel> consultarPorId(Long id);
}

