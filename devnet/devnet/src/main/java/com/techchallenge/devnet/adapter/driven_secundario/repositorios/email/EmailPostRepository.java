package com.techchallenge.devnet.adapter.driven_secundario.repositorios.email;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.daos.EmailDao;
import com.techchallenge.devnet.core.application.ports.saida.email.IEmailSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmailPostRepository implements IEmailSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final EmailRepositoryJpa jpa;

  public EmailPostRepository(IAdapterSaida mapper,
                             EmailRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Override
  public EmailModel salvar(final EmailModel emailModel) {

    return Optional.of(emailModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, EmailDao.class))
      .map(this.jpa::save)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, EmailModel.class))
      .orElseThrow();
  }
}

