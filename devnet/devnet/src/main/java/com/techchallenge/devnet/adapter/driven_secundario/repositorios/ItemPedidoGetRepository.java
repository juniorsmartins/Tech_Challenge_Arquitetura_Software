package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.entities.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemPedidoGetRepository implements IItemPedidoRepository.GetRepository {

  @Autowired
  private ItemPedidoRepositoryJpa itemPedidoRepositoryJpa;

  @Override
  public Optional<ItemPedido> consultarPorId(final Long id) {
    return this.itemPedidoRepositoryJpa.findById(id);
  }
}
