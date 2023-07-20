package com.techchallenge.devnet.adapter.driven_secundario.repositorios.email;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.EmailEntity;
import com.techchallenge.devnet.core.application.ports.saida.email.IEmailSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmailPostRepository implements IEmailSalvarRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

  @Autowired
  private EmailRepositoryJpa jpa;

  @Override
  public EmailModel salvar(final EmailModel emailModel) {

    return Optional.of(emailModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, EmailEntity.class))
      .map(this.jpa::save)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, EmailModel.class))
      .orElseThrow();
  }
}

