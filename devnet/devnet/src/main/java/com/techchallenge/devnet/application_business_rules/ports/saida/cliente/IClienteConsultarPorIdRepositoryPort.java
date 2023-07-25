package com.techchallenge.devnet.application_business_rules.ports.saida.cliente;

import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;

import java.util.Optional;

public interface IClienteConsultarPorIdRepositoryPort {

  Optional<ClienteModel> consultarPorId(Long id);
}

