package com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;

import java.util.Optional;

public interface ItemPedidoConsultarPorIdRepositoryPort {

  Optional<ItemPedidoModel> consultarPorId(Long id);
}

