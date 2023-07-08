package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ItemPedidoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemPedidoPostRepositoryAdapter implements IItemPedidoRepository.PostRepository {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private ItemPedidoRepositoryJpa jpa;

  @Override
  public ItemPedidoModel salvar(final ItemPedidoModel itemPedidoModel) {

    return Optional.of(itemPedidoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ItemPedidoEntity.class))
      .map(this.jpa::save)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ItemPedidoModel.class))
      .orElseThrow();
  }

  @Override
  public void flush() {
    this.jpa.flush();
  }
}

