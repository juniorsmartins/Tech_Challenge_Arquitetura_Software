package com.techchallenge.devnet.interface_adapters.driven_secundario.gateways;

import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.PagamentoGatewayPort;
import org.springframework.stereotype.Component;

@Component
public class PagamentoGateway implements PagamentoGatewayPort {

  @Override
  public Boolean verificarStatusNoGateway(final Long idPedido) {
    return true;
  }
}

