package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.entities.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemPedidoPostRepository implements IItemPedidoRepository.PostRepository {

  @Autowired
  private ItemPedidoRepositoryJpa itemPedidoRepositoryJpa;

  @Override
  public ItemPedido salvar(final ItemPedido itemPedido) {
    return this.itemPedidoRepositoryJpa.save(itemPedido);
  }
}

