package com.techchallenge.devnet.application_business_rules.exceptions;

public final class ArmazemException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ArmazemException(String mensagem) {
    super(mensagem);
  }

  public ArmazemException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}

