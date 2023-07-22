package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.pedido.IPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PedidoPostRepository implements IPedidoSalvarRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

  @Autowired
  private PedidoRepositoryJpa jpa;

  @Transactional
  @Override
  public PedidoModel salvar(final PedidoModel pedidoModel) {

    return Optional.of(pedidoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, PedidoEntity.class))
      .map(this.jpa::saveAndFlush)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, PedidoModel.class))
      .orElseThrow();
  }
}

