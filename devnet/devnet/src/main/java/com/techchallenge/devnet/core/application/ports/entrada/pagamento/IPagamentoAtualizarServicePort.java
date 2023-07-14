package com.techchallenge.devnet.core.application.ports.entrada.pagamento;

import com.techchallenge.devnet.core.domain.models.PagamentoModel;

public interface IPagamentoAtualizarServicePort {

  PagamentoModel verificarStatusNoGateway(Long idPedido);
}

