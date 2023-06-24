package com.techchallenge.devnet.core.domain.base.exceptions.http_400;

public abstract class RequisicaoMalFormuladaException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RequisicaoMalFormuladaException(String mensagem) {
    super(mensagem);
  }
}

