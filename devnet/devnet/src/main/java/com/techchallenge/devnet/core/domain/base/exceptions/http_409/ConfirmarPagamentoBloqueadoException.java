package com.techchallenge.devnet.core.domain.base.exceptions.http_409;

import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;

public final class ConfirmarPagamentoBloqueadoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public ConfirmarPagamentoBloqueadoException(String mensagem) {
    super(mensagem);
  }

  public ConfirmarPagamentoBloqueadoException(Long id, StatusPedidoEnum statusPedido) {
    super(String.format(MensagemPadrao.PAGAMENTO_BLOQUEADO, id, statusPedido.toString()));
  }
}

