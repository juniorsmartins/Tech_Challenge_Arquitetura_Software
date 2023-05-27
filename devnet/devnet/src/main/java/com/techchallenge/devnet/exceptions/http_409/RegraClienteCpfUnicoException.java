package com.techchallenge.devnet.exceptions.http_409;

import com.techchallenge.devnet.exceptions.MensagemPadrao;

public final class RegraClienteCpfUnicoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public RegraClienteCpfUnicoException(String cpf) {
    super(String.format(MensagemPadrao.CPF_JA_CADASTRADO, cpf));
  }
}

