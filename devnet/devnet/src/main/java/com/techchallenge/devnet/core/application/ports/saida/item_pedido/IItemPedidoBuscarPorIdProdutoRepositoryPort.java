package com.techchallenge.devnet.core.application.ports.saida.item_pedido;

import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;

import java.util.List;

public interface IItemPedidoBuscarPorIdProdutoRepositoryPort {

  List<ItemPedidoModel> buscarPorIdDeProduto(Long idProduto);
}

