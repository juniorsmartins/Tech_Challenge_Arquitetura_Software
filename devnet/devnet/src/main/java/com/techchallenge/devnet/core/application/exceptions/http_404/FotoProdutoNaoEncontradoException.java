package com.techchallenge.devnet.core.application.exceptions.http_404;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;

public final class FotoProdutoNaoEncontradoException extends RecursoNaoEncontradoException {

  private static final long serialVersionUID = 1L;

  public FotoProdutoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public FotoProdutoNaoEncontradoException(Long id) {
    this(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
  }

}

