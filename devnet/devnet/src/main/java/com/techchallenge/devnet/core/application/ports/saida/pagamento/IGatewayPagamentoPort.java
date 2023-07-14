package com.techchallenge.devnet.core.application.ports.saida;

public interface IGatewayPagamentoPort {
  Boolean verificarStatusNoGateway(Long idPedido);
}

