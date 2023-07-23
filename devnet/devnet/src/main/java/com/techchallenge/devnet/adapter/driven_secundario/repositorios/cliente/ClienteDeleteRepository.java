package com.techchallenge.devnet.adapter.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ClienteEntity;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteApagarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClienteDeleteRepository implements IClienteApagarRepositoryPort {

  private final IAdapterSaida mapper;

  private final ClienteRepositoryJpa jpa;

  public ClienteDeleteRepository(IAdapterSaida mapper,
                                 ClienteRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

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

