package com.techchallenge.devnet.repositories;

import com.techchallenge.devnet.models.Cliente;
import com.techchallenge.devnet.repositories.jpas.ClienteRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClienteConsultarRepositoryImpl implements PoliticaRepository.ClienteConsultarRepository {

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

