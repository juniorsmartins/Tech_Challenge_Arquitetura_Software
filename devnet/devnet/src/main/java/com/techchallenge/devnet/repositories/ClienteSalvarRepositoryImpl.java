package com.techchallenge.devnet.repositories;

import com.techchallenge.devnet.models.Cliente;
import com.techchallenge.devnet.repositories.jpas.ClienteRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteSalvarRepositoryImpl implements PoliticaRepository.ClienteSalvarRepository<Cliente> {

  @Autowired
  private ClienteRepositoryJpa jpa;

  @Override
  public Cliente salvar(Cliente cliente) {

    return this.jpa.save(cliente);
  }
}

