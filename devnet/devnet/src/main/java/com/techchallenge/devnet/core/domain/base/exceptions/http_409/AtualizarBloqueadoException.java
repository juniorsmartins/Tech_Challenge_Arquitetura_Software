package com.techchallenge.devnet.core.domain.base.exceptions.http_409;

import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;

public final class AtualizarBloqueadoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public AtualizarBloqueadoException(String mensagem) {
    super(mensagem);
  }

  public AtualizarBloqueadoException(Long id, StatusPedidoEnum statusPedido) {
    super(String.format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, id, statusPedido.toString()));
  }
}

