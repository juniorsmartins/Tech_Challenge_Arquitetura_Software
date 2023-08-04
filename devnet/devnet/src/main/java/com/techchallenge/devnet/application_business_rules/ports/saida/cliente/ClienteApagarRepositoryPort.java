package com.techchallenge.devnet.application_business_rules.ports.saida.cliente;

import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;

public interface ClienteApagarRepositoryPort {

  void deletar(ClienteModel clienteModel);
}

