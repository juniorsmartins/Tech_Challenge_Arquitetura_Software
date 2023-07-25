package com.techchallenge.devnet.application_business_rules.ports.saida.pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

import java.util.Optional;

public interface PedidoConsultarPorIdRepositoryPort {

  Optional<PedidoModel> consultarPorId(Long id);
}

