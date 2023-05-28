package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.domain.value_objects.ClienteFiltro;
import com.techchallenge.devnet.core.domain.value_objects.ClienteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteGetRepositoryImpl implements IClienteRepository.GetRepository {

  @Autowired
  private ClienteRepositoryJpa repositoryJpa;

  @Override
  public Page<Cliente> pesquisar(final ClienteFiltro filtro, final Pageable paginacao) {

    return this.repositoryJpa.findAll(ClienteSpecification.consultaDinamica(filtro), paginacao);
  }

  @Override
  public Optional<Cliente> consultarPorId(final Long id) {

    return this.repositoryJpa.findById(id);
  }

  @Override
  public Optional<Cliente> consultarPorCpf(final String cpf) {

    return this.repositoryJpa.findByCpf(cpf);
  }
}

