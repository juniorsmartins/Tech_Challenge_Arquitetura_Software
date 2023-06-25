package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientePostRepository implements IClienteRepository.PostRepository {

  @Autowired
  private ClienteRepositoryJpa jpa;

  @Override
  public Cliente salvar(final Cliente cliente) {
    return this.jpa.save(cliente);
  }
}

