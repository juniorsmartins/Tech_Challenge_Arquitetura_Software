package com.techchallenge.devnet.adapter.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.adapter.driven_secundario.adapters_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ClienteEntity;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteApagarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClienteApagarRepository implements IClienteApagarRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

  @Autowired
  private ClienteRepositoryJpa jpa;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final ClienteModel clienteModel) {

    Optional.of(clienteModel)
      .map(model -> {
        var clienteEntity = this.mapper.converterOrigemParaDestino(model, ClienteEntity.class);
        this.jpa.delete(clienteEntity);
        return true;
      })
      .orElseThrow();
  }
}

