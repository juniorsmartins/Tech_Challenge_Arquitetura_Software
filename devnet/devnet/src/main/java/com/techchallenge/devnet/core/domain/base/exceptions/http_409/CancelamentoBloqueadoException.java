package com.techchallenge.devnet.core.domain.base.exceptions.http_409;

import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;

public final class CancelamentoBloqueadoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public CancelamentoBloqueadoException(String mensagem) {
    super(mensagem);
  }

  public CancelamentoBloqueadoException(Long id, StatusPedidoEnum statusPedido) {
    super(String.format(MensagemPadrao.CANCELAMENTO_BLOQUEADO, id, statusPedido.toString()));
  }
}

