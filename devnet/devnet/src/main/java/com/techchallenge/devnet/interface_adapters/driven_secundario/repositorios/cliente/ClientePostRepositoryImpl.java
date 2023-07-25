package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ClienteDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClientePostRepositoryImpl implements ClienteSalvarRepositoryPort {

  private final AdapterSaida mapper;

  private final ClienteRepositoryJpa jpa;

  public ClientePostRepositoryImpl(AdapterSaida mapper,
                                   ClienteRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional
  @Override
  public ClienteModel salvar(final ClienteModel clienteModel) {

    return Optional.of(clienteModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ClienteDao.class))
      .map(this.jpa::saveAndFlush)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ClienteModel.class))
      .orElseThrow();
  }
}

