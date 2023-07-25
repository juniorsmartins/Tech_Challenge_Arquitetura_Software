package com.techchallenge.devnet.adapter.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.daos.ClienteDao;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClientePostRepository implements IClienteSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final ClienteRepositoryJpa jpa;

  public ClientePostRepository(IAdapterSaida mapper,
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

