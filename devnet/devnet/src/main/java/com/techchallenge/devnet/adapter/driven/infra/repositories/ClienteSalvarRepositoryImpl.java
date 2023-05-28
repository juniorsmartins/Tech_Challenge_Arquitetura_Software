package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.core.application.ports.PoliticaClienteRepository;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteSalvarRepositoryImpl implements PoliticaClienteRepository.ClienteSalvarRepository<Cliente> {

  @Autowired
  private ClienteRepositoryJpa jpa;

  @Override
  public Cliente salvar(Cliente cliente) {

    return this.jpa.save(cliente);
  }
}

