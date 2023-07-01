package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.EmailModel;

public interface IEmailServicePort {

  interface EnviarService {
    EmailModel enviar(EmailModel emailModel);
  }
}

