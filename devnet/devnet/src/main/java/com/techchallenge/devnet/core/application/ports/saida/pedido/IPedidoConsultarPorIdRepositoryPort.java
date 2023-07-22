package com.techchallenge.devnet.core.application.ports.saida.pedido;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

import java.util.Optional;

public interface IPedidoConsultarPorIdRepositoryPort {

  Optional<PedidoModel> consultarPorId(Long id);
}

