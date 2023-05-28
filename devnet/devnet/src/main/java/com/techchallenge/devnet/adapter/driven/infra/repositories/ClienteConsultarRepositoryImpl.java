package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.core.application.ports.PoliticaClienteRepository;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteConsultarRepositoryImpl implements PoliticaClienteRepository.ClienteConsultarRepository {

  @Autowired
  private ClienteRepositoryJpa repositoryJpa;

  @Override
  public Optional<Cliente> consultarPorId(Long id) {

    return this.repositoryJpa.findById(id);
  }

  @Override
  public Optional<Cliente> consultarPorCpf(String cpf) {

    return this.repositoryJpa.findByCpf(cpf);
  }
}

