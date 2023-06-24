package com.techchallenge.devnet.core.domain.base.exceptions.http_409;

import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;

public final class AtualizarPedidoBloqueadoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public AtualizarPedidoBloqueadoException(String mensagem) {
    super(mensagem);
  }

  public AtualizarPedidoBloqueadoException(Long id, StatusPedidoEnum statusPedido) {
    super(String.format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, id, statusPedido.toString()));
  }
}

