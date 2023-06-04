package com.techchallenge.devnet.core.domain.base.exceptions.http_404;

public abstract class RecursoNaoEncontradoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RecursoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }
}

