package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ClienteDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteApagarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClienteDeleteRepositoryImpl implements ClienteApagarRepositoryPort {

  private final AdapterSaida mapper;

  private final ClienteRepositoryJpa jpa;

  public ClienteDeleteRepositoryImpl(AdapterSaida mapper,
                                     ClienteRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final ClienteModel clienteModel) {

    Optional.of(clienteModel)
      .map(model -> {
        var clienteEntity = this.mapper.converterOrigemParaDestino(model, ClienteDao.class);
        this.jpa.delete(clienteEntity);
        return true;
      })
      .orElseThrow();
  }
}

