package com.techchallenge.devnet.application_business_rules.exceptions.http_404;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;

public final class ClienteNaoEncontradoException extends RecursoNaoEncontradoException {

  private static final long serialVersionUID = 1L;

  public ClienteNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public ClienteNaoEncontradoException(Long id) {
    this(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, id));
  }
}

