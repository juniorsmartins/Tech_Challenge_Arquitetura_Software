package com.techchallenge.devnet.interface_adapters.driven_secundario.gateways;

import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.IGatewayPagamentoPort;
import org.springframework.stereotype.Component;

@Component
public class PagamentoGateway implements IGatewayPagamentoPort {

  @Override
  public Boolean verificarStatusNoGateway(final Long idPedido) {
    return true;
  }
}

