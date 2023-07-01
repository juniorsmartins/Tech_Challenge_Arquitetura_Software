package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemPedidoPostRepository implements IItemPedidoRepository.PostRepository {

  @Autowired
  private ItemPedidoRepositoryJpa itemPedidoRepositoryJpa;

  @Override
  public ItemPedidoModel salvar(final ItemPedidoModel itemPedido) {
    return this.itemPedidoRepositoryJpa.save(itemPedido);
  }
}

