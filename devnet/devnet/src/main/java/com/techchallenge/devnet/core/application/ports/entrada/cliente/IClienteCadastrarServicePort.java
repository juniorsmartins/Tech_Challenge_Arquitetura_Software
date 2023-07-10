package com.techchallenge.devnet.core.application.ports.entrada.cliente;

import com.techchallenge.devnet.core.domain.models.ClienteModel;

public interface IClienteCadastrarServicePort {
  ClienteModel cadastrar(ClienteModel cliente);
}

