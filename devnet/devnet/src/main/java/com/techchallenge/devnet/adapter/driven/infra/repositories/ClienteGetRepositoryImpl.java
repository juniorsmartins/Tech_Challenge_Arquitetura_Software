package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.domain.value_objects.ClienteFiltro;
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
  public Page<Cliente> pesquisar(ClienteFiltro filtro, Pageable paginacao) {

    return null;
  }

  @Override
  public Optional<Cliente> consultarPorId(Long id) {

    return this.repositoryJpa.findById(id);
  }

  @Override
  public Optional<Cliente> consultarPorCpf(String cpf) {

    return this.repositoryJpa.findByCpf(cpf);
  }
}

