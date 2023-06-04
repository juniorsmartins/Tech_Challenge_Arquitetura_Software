package com.techchallenge.devnet.core.domain.base.exceptions.http_404;

import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;

public final class ProdutoNaoEncontradoException extends RecursoNaoEncontradoException {

  public ProdutoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public ProdutoNaoEncontradoException(Long id) {
    this(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
  }
}

