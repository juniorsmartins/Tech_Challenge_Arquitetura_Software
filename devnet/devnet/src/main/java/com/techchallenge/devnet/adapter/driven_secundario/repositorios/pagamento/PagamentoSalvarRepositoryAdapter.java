package com.techchallenge.devnet.adapter.driven_secundario.repositorios.pagamento;

import com.techchallenge.devnet.adapter.driven_secundario.adapters_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PagamentoEntity;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IPagamentoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PagamentoSalvarRepositoryAdapter implements IPagamentoSalvarRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

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

