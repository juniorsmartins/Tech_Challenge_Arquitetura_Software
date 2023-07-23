package com.techchallenge.devnet.adapter.driven_secundario.repositorios.pedido;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.core.application.ports.saida.pedido.IPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PedidoPostRepository implements IPedidoSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final PedidoRepositoryJpa jpa;

  public PedidoPostRepository(IAdapterSaida mapper,
                              PedidoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

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

