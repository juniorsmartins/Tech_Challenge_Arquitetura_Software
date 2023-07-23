package com.techchallenge.devnet.core.application.exceptions.http_400;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;

public final class DataNascimentoInvalidaException extends RequisicaoMalFormuladaException {

  public static final long serialVersionUID = 1L;

  public DataNascimentoInvalidaException(String dataNascimento) {
    super(String.format(MensagemPadrao.DATA_NASCIMENTO_INVALIDA, dataNascimento));
  }
}

