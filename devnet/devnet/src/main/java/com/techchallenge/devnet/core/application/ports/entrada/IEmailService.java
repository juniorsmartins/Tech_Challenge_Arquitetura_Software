package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.EmailDtoResponse;

public interface IEmailService {

  interface EnviarService {
    EmailDtoResponse enviar(EmailDtoRequest dtoRequest);
  }
}

