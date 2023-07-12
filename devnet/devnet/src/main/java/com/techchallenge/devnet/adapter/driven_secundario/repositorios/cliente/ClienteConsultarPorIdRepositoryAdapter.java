package com.techchallenge.devnet.adapter.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClienteConsultarPorIdRepositoryAdapter implements IClienteConsultarPorIdRepositoryPort {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private ClienteRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Optional<ClienteModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ClienteModel.class));
  }
}

