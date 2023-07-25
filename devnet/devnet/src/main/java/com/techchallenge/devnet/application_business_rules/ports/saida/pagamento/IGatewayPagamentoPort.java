package com.techchallenge.devnet.application_business_rules.ports.saida.pagamento;

public interface IGatewayPagamentoPort {
  Boolean verificarStatusNoGateway(Long idPedido);
}

