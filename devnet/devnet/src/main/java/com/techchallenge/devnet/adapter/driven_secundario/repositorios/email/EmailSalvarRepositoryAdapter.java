package com.techchallenge.devnet.adapter.driven_secundario.repositorios.email;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.EmailEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.email.EmailRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.email.IEmailSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmailSalvarRepositoryAdapter implements IEmailSalvarRepositoryPort {

  @Autowired
  private IMapperSaida mapper;

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

