package com.techchallenge.devnet.application_business_rules.exceptions.http_404;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;

public final class PedidoNaoEncontradoException extends RecursoNaoEncontradoException {

  private static final long serialVersionUID = 1L;

  public PedidoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public PedidoNaoEncontradoException(Long id) {
    this(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
  }
}

