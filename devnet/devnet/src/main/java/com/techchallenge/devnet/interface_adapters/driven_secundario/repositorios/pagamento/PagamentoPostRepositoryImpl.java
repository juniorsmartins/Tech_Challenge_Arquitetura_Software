package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.pagamento;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.PagamentoDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.PagamentoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PagamentoPostRepositoryImpl implements PagamentoSalvarRepositoryPort {

  private final AdapterSaida mapper;

  private final PagamentoRepositoryJpa jpa;

  public PagamentoPostRepositoryImpl(AdapterSaida mapper,
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

