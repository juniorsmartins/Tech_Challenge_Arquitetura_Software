package com.techchallenge.devnet.application_business_rules.exceptions.http_400;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;

public final class IdsIncompativeis extends RequisicaoMalFormuladaException {

  public static final long serialVersionUID = 1L;

  public IdsIncompativeis(String mensagem) {
    super(mensagem);
  }

  public IdsIncompativeis(Long id1, Long id2) {
    super(String.format(MensagemPadrao.IDS_INCOMPATIVEIS, id1, id2));
  }
}

