package com.techchallenge.devnet.adapter.driven_secundario.gateways;

import com.techchallenge.devnet.core.application.ports.saida.pagamento.IGatewayPagamentoPort;
import org.springframework.stereotype.Component;

@Component
public class PagamentoGateway implements IGatewayPagamentoPort {

  @Override
  public Boolean verificarStatusNoGateway(final Long idPedido) {
    return true;
  }
}

