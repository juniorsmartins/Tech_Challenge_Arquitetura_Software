package com.techchallenge.devnet.application_business_rules.ports.entrada.copa;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface CopaPedidoFinalizadoServicePort {

  PedidoModel confirmarPedidoFinalizado(Long idPedido);
}

