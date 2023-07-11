package com.techchallenge.devnet.core.application.ports.entrada.cliente;

import com.techchallenge.devnet.core.domain.models.ClienteModel;

public interface IClienteAtualizarServicePort {
  ClienteModel atualizar(Long id, ClienteModel clienteModel);
}

