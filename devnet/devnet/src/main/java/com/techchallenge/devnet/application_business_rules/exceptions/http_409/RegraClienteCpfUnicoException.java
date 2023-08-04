package com.techchallenge.devnet.application_business_rules.exceptions.http_409;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;

public final class RegraClienteCpfUnicoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public RegraClienteCpfUnicoException(String cpf) {
    super(String.format(MensagemPadrao.CPF_JA_CADASTRADO, cpf));
  }
}

