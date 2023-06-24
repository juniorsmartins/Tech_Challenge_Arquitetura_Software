package com.techchallenge.devnet.core.domain.base.exceptions.http_404;

import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;

public final class PagamentoNaoEncontradoException extends RecursoNaoEncontradoException {

  private static final long serialVersionUID = 1L;

  public PagamentoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public PagamentoNaoEncontradoException(Long id) {
    super(String.format(MensagemPadrao.PAGAMENTO_NAO_ENCONTRADO, id));
  }
}

