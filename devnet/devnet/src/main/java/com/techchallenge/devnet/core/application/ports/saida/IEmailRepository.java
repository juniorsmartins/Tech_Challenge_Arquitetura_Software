package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.EmailModel;

public interface IEmailRepository {

  interface PostRepository {
    EmailModel salvar(EmailModel email);
  }
}

