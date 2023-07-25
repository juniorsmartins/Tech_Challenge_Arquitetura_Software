package com.techchallenge.devnet.application_business_rules.ports.entrada.copa;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface ICopaPedidoFinalizadoServicePort {

  PedidoModel confirmarPedidoFinalizado(Long idPedido);
}

