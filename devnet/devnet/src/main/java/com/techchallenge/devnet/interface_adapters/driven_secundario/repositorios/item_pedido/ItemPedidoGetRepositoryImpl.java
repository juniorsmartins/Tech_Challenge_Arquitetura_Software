package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.item_pedido;

import com.techchallenge.devnet.frameworks_and_drivers.db.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.IItemPedidoBuscarPorIdProdutoRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.IItemPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemPedidoGetRepositoryImpl implements IItemPedidoConsultarPorIdRepositoryPort,
  IItemPedidoBuscarPorIdProdutoRepositoryPort {

  private final AdapterSaida mapper;

  private final ItemPedidoRepositoryJpa jpa;

  public ItemPedidoGetRepositoryImpl(AdapterSaida mapper,
                                     ItemPedidoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

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
