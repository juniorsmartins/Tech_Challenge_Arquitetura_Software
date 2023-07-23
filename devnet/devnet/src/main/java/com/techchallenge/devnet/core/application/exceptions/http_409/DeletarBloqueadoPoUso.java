package com.techchallenge.devnet.core.application.exceptions.http_409;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;

public final class DeletarBloqueadoPoUso extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public DeletarBloqueadoPoUso(String mensagem) {
    super(mensagem);
  }

  public DeletarBloqueadoPoUso(Long id) {
    this(String.format(MensagemPadrao.BLOQUEADO_POR_USO, id));
  }
}

