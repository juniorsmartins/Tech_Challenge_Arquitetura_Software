package com.techchallenge.devnet.adapter.driven_secundario.repositorios.item_pedido;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ItemPedidoEntity;
import com.techchallenge.devnet.core.application.ports.saida.item_pedido.IItemPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ItemPedidoPostRepository implements IItemPedidoSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final ItemPedidoRepositoryJpa jpa;

  public ItemPedidoPostRepository(IAdapterSaida mapper,
                                  ItemPedidoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional
  @Override
  public ItemPedidoModel salvar(final ItemPedidoModel itemPedidoModel) {

    return Optional.of(itemPedidoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ItemPedidoEntity.class))
      .map(this.jpa::save)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ItemPedidoModel.class))
      .orElseThrow();
  }

//  @Override
//  public void flush() {
//    this.jpa.flush();
//  }
}

