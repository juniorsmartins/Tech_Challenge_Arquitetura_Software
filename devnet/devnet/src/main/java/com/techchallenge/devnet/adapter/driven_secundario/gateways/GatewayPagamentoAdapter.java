package com.techchallenge.devnet.adapter.driven_secundario.gateways;

import com.techchallenge.devnet.core.application.ports.saida.IGatewayPagamentoPort;
import org.springframework.stereotype.Component;

@Component
public class GatewayPagamentoAdapter implements IGatewayPagamentoPort.GetGateway {

  @Override
  public Boolean verificarStatusNoGateway(Long idPedido) {
    return true;
  }
}

