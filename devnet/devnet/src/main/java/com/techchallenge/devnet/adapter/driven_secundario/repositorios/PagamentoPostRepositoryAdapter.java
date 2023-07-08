package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.conversor_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PagamentoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PagamentoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PagamentoPostRepositoryAdapter implements IPagamentoRepositoryPort.PostRepository {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private PagamentoRepositoryJpa jpa;

  @Transactional
  @Override
  public PagamentoModel salvar(final PagamentoModel pagamentoModel) {

    return Optional.of(pagamentoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, PagamentoEntity.class))
      .map(this.jpa::save)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, PagamentoModel.class))
      .orElseThrow();
  }
}

