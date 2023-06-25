package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.EmailRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IEmailRepository;
import com.techchallenge.devnet.core.domain.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmailPostRepository implements IEmailRepository.PostRepository {

  @Autowired
  private EmailRepositoryJpa emailRepositoryJpa;

  @Override
  public Email salvar(final Email email) {
    return this.emailRepositoryJpa.save(email);
  }
}

