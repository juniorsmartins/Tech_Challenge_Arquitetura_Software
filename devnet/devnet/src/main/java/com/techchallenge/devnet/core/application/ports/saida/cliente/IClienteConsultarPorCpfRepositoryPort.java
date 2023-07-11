package com.techchallenge.devnet.core.application.ports.saida.cliente;

import com.techchallenge.devnet.core.domain.models.ClienteModel;

import java.util.Optional;

public interface IClienteConsultarPorCpfRepositoryPort {

  Optional<ClienteModel> consultarPorCpf(String cpf);
}

