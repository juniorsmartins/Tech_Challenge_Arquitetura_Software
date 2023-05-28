package com.techchallenge.devnet.core.exceptions.http_409;

public abstract class RegraDeNegocioVioladaException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RegraDeNegocioVioladaException(String mensagem) {
    super(mensagem);
  }
}

