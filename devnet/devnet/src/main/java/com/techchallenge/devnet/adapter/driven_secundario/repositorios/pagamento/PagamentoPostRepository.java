package com.techchallenge.devnet.adapter.driven_secundario.repositorios.pagamento;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.daos.PagamentoDao;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IPagamentoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PagamentoPostRepository implements IPagamentoSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final PagamentoRepositoryJpa jpa;

  public PagamentoPostRepository(IAdapterSaida mapper,
                                 PagamentoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional
  @Override
  public PagamentoModel salvar(final PagamentoModel pagamentoModel) {

    return Optional.of(pagamentoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, PagamentoDao.class))
      .map(this.jpa::save)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, PagamentoModel.class))
      .orElseThrow();
  }
}

