package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.item_pedido.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.item_pedido.IItemPedidoBuscarPorIdProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.item_pedido.IItemPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemPedidoGetRepository implements IItemPedidoConsultarPorIdRepositoryPort,
  IItemPedidoBuscarPorIdProdutoRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

  @Autowired
  private ItemPedidoRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Optional<ItemPedidoModel> consultarPorId(final Long id) {
    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ItemPedidoModel.class));
  }

  @Transactional(readOnly = true)
  @Override
  public List<ItemPedidoModel> buscarPorIdDeProduto(final Long produtoId) {

    return Optional.of(produtoId)
      .map(id -> this.jpa.findByProdutoId(id))
      .map(entities -> this.mapper.converterListaOrigemParaListaDestino(entities, ItemPedidoModel.class))
      .orElseThrow();
  }
}
