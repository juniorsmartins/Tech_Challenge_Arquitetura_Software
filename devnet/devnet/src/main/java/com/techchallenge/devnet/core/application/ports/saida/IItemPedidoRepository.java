package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;

import java.util.List;
import java.util.Optional;

public interface IItemPedidoRepository {

  interface PostRepository {
    ItemPedidoModel salvar(ItemPedidoModel itemPedido);

    void flush();
  }

  interface GetRepository {
    Optional<ItemPedidoModel> consultarPorId(Long id);

    List<ItemPedidoModel> consultarPorIdDeProduto(Long idProduto);
  }

  interface DeleteRepository {
    void deletar(ItemPedidoModel itemPedido);

    void deletarItensDoPedido(Long idPedido);
  }
}

