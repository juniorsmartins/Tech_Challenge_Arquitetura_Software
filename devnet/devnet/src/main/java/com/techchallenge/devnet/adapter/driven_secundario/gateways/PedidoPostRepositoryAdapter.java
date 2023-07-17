package com.techchallenge.devnet.adapter.driven_secundario.gateways;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.gateways.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PedidoPostRepositoryAdapter implements IPedidoRepositoryPort.PostRepository {

  @Autowired
  private IMapperSaida mapper;

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

