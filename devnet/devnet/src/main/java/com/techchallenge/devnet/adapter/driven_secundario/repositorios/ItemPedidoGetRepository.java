package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemPedidoGetRepository implements IItemPedidoRepository.GetRepository {

  @Autowired
  private ItemPedidoRepositoryJpa itemPedidoRepositoryJpa;

  @Override
  public Optional<ItemPedidoModel> consultarPorId(final Long id) {
    return this.itemPedidoRepositoryJpa.findById(id);
  }

  @Override
  public List<ItemPedidoModel> consultarPorIdDeProduto(Long produtoId) {
    return this.itemPedidoRepositoryJpa.findByProdutoId(produtoId);
  }
}
