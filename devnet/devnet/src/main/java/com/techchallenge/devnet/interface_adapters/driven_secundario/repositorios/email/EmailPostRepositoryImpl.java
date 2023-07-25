package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.email;

import com.techchallenge.devnet.frameworks_and_drivers.db.EmailRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.EmailDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.email.IEmailSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.EmailModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmailPostRepositoryImpl implements IEmailSalvarRepositoryPort {

  private final AdapterSaida mapper;

  private final EmailRepositoryJpa jpa;

  public EmailPostRepositoryImpl(AdapterSaida mapper,
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

