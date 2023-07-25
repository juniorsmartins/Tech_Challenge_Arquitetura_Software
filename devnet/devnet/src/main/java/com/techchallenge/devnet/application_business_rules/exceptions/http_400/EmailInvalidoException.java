package com.techchallenge.devnet.application_business_rules.exceptions.http_400;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;

public final class EmailInvalidoException extends RequisicaoMalFormuladaException {

  public static final long serialVersionUID = 1L;

  public EmailInvalidoException(String email) {
    super(String.format(MensagemPadrao.EMAIL_INVALIDO, email));
  }
}

