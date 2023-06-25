package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.entities.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemPedidoDeleteRepository implements IItemPedidoRepository.DeleteRepository {

  @Autowired
  private ItemPedidoRepositoryJpa itemPedidoRepositoryJpa;

  @Override
  public void deletar(final ItemPedido itemPedido) {
    this.itemPedidoRepositoryJpa.delete(itemPedido);
  }
}

