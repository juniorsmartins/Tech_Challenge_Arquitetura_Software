package com.techchallenge.devnet.application_business_rules.ports.saida.pagamento;

public interface PagamentoGatewayPort {
  Boolean verificarStatusNoGateway(Long idPedido);
}

