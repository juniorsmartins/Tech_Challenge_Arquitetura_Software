package com.techchallenge.devnet.application_business_rules.exceptions.http_409;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;

public final class ConfirmarPagamentoBloqueadoException extends RegraDeNegocioVioladaException {

  public static final long serialVersionUID = 1L;

  public ConfirmarPagamentoBloqueadoException(String mensagem) {
    super(mensagem);
  }

  public ConfirmarPagamentoBloqueadoException(Long id, StatusPedidoEnum statusPedido) {
    super(String.format(MensagemPadrao.PAGAMENTO_BLOQUEADO, id, statusPedido.toString()));
  }
}

