package com.techchallenge.devnet.adapter.driven_secundario.repositorios.pagamento;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IPagamentoConsultarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PagamentoConsultarRepositoryAdapter implements IPagamentoConsultarRepositoryPort {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private PagamentoRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Optional<PagamentoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, PagamentoModel.class));
  }
}

