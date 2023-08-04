package com.techchallenge.devnet.application_business_rules.exceptions.http_409;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;

public final class CancelamentoBloqueadoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public CancelamentoBloqueadoException(String mensagem) {
    super(mensagem);
  }

  public CancelamentoBloqueadoException(Long id, StatusPedidoEnum statusPedido) {
    super(String.format(MensagemPadrao.CANCELAMENTO_BLOQUEADO, id, statusPedido.toString()));
  }
}

