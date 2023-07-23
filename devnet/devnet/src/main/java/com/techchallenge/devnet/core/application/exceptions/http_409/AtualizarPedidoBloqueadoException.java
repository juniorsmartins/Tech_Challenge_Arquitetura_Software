package com.techchallenge.devnet.core.application.exceptions.http_409;

public final class AtualizarPedidoBloqueadoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public AtualizarPedidoBloqueadoException(String mensagem) {
    super(mensagem);
  }
}

