package com.techchallenge.devnet.application_business_rules.exceptions.http_404;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;

public final class ProdutoNaoEncontradoException extends RecursoNaoEncontradoException {

  private static final long serialVersionUID = 1L;

  public ProdutoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public ProdutoNaoEncontradoException(Long id) {
    this(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
  }
}

