package com.techchallenge.devnet.core.application.ports.saida;

public interface IGatewayPagamentoPort {

  interface GetGateway {
    Boolean verificarStatusNoGateway(Long idPedido);
  }
}

