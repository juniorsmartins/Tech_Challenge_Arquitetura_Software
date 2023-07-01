package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.entities.EmailEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.EmailRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.application.ports.saida.IEmailRepositoryPort;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmailPostRepositoryAdapter implements IEmailRepositoryPort.PostRepository {

  @Autowired
  private IMapper mapper;

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

