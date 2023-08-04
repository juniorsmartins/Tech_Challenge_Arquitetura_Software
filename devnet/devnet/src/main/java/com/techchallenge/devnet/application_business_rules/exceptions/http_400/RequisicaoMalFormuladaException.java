package com.techchallenge.devnet.application_business_rules.exceptions.http_400;

public abstract class RequisicaoMalFormuladaException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RequisicaoMalFormuladaException(String mensagem) {
    super(mensagem);
  }
}

