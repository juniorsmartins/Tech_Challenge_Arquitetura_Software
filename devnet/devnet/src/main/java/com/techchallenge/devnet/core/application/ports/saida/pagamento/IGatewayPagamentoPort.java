package com.techchallenge.devnet.core.application.ports.saida.pagamento;

public interface IGatewayPagamentoPort {
  Boolean verificarStatusNoGateway(Long idPedido);
}

