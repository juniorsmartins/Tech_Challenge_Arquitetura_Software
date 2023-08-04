package com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento;

import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;

public interface PagamentoAtualizarServicePort {

  PagamentoModel verificarStatusNoGateway(Long idPedido);
}

