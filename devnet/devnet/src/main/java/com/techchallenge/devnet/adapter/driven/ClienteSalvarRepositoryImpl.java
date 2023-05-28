package com.techchallenge.devnet.adapter.driven;

import com.techchallenge.devnet.core.application.ports.PoliticaRepository;
import com.techchallenge.devnet.core.domain.Cliente;
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

