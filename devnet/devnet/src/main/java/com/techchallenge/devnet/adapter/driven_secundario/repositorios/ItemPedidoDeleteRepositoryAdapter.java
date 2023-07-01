package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.entities.ItemPedidoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemPedidoDeleteRepositoryAdapter implements IItemPedidoRepository.DeleteRepository {

  @Autowired
  private IMapper mapper;

  @Autowired
  private ItemPedidoRepositoryJpa jpa;

  @Override
  public void deletar(final ItemPedidoModel itemPedidoModel) {

    Optional.of(itemPedidoModel)
      .map(model -> {
        var itemPedidoEntity = this.mapper.converterOrigemParaDestino(model, ItemPedidoEntity.class);
        this.jpa.delete(itemPedidoEntity);
        return true;
      })
      .orElseThrow();
  }
}

