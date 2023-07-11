package com.techchallenge.devnet.core.application.ports.saida.cliente;

import com.techchallenge.devnet.core.domain.models.ClienteModel;

public interface IClienteSalvarRepositoryPort {
  ClienteModel salvar(ClienteModel clienteModel);
}

