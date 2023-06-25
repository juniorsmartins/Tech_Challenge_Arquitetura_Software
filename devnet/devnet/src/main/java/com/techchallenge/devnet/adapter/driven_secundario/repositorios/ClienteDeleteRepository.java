package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepository;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDeleteRepository implements IClienteRepository.DeleteRepository {

  @Autowired
  private ClienteRepositoryJpa repositoryJpa;

  @Override
  public void deletar(final Cliente cliente) {

    this.repositoryJpa.delete(cliente);
  }
}

