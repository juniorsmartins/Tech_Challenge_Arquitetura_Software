package com.techchallenge.devnet.adapter.driven_secundario.gateways.pagamento;

import com.techchallenge.devnet.core.application.ports.saida.pagamento.IGatewayPagamentoPort;
import org.springframework.stereotype.Component;

@Component
public class GatewayPagamentoAdapter implements IGatewayPagamentoPort {

  @Override
  public Boolean verificarStatusNoGateway(final Long idPedido) {
    return true;
  }
}

