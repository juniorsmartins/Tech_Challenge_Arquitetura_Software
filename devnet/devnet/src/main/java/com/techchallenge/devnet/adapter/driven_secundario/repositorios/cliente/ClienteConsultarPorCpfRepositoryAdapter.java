package com.techchallenge.devnet.adapter.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorCpfRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClienteConsultarPorCpfRepositoryAdapter implements IClienteConsultarPorCpfRepositoryPort {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private ClienteRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Optional<ClienteModel> consultarPorCpf(final String cpf) {

    return this.jpa.findByCpf(cpf)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ClienteModel.class));
  }
}

