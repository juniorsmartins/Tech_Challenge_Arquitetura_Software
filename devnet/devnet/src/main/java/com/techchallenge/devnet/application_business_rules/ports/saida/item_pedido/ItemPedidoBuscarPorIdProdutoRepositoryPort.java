package com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;

import java.util.List;

public interface ItemPedidoBuscarPorIdProdutoRepositoryPort {

  List<ItemPedidoModel> buscarPorIdDeProduto(Long idProduto);
}

