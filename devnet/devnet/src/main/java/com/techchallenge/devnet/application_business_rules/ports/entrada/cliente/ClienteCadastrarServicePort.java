package com.techchallenge.devnet.application_business_rules.ports.entrada.cliente;

import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;

public interface ClienteCadastrarServicePort {
  ClienteModel cadastrar(ClienteModel cliente);
}

