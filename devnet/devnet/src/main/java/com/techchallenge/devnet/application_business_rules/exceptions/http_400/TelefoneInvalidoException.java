package com.techchallenge.devnet.application_business_rules.exceptions.http_400;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;

public final class TelefoneInvalidoException extends RequisicaoMalFormuladaException {

  public static final long serialVersionUID = 1L;

  public TelefoneInvalidoException(String numeroTelefone) {
    super(String.format(MensagemPadrao.TELEFONE_INVALIDO, numeroTelefone));
  }
}

