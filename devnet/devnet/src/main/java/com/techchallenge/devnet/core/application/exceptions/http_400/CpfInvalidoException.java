package com.techchallenge.devnet.core.application.exceptions.http_400;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;

public final class CpfInvalidoException extends RequisicaoMalFormuladaException {

  public static final long serialVersionUID = 1L;

  public CpfInvalidoException(String cpf) {
    super(String.format(MensagemPadrao.CPF_INVALIDO, cpf));
  }
}

