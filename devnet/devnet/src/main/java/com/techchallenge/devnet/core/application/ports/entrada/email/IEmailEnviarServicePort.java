package com.techchallenge.devnet.core.application.ports.entrada.email;

import com.techchallenge.devnet.core.domain.models.EmailModel;

public interface IEmailEnviarServicePort {

  EmailModel enviar(EmailModel emailModel);
}

