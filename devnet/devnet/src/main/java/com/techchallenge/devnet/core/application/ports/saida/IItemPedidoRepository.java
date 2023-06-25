package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.entities.ItemPedido;

import java.util.List;
import java.util.Optional;

public interface IItemPedidoRepository {

  interface PostRepository {
    ItemPedido salvar(ItemPedido itemPedido);
  }

  interface GetRepository {
    Optional<ItemPedido> consultarPorId(Long id);

    List<ItemPedido> consultarPorIdDeProduto(Long idProduto);
  }

  interface DeleteRepository {
    void deletar(ItemPedido itemPedido);
  }
}

