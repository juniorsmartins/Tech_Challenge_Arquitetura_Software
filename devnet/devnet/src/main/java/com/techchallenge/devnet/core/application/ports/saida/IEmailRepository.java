package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.Email;

public interface IEmailRepository {

  interface PostRepository {
    Email salvar(Email email);
  }
}

