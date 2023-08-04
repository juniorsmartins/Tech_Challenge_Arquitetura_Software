package com.techchallenge.devnet.application_business_rules.use_case.cliente;

import com.techchallenge.devnet.application_business_rules.ports.entrada.cliente.ClienteCadastrarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.base.assertions_concern.RegrasCliente;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostServiceImpl implements ClienteCadastrarServicePort {

  private final ClienteSalvarRepositoryPort repositorioSalvarCliente;

  private final List<RegrasCliente> regras;

  public ClientePostServiceImpl(ClienteSalvarRepositoryPort repositorioSalvarCliente,
                                List<RegrasCliente> regras) {
    this.repositorioSalvarCliente = repositorioSalvarCliente;
    this.regras = regras;
  }

  @Override
  public ClienteModel cadastrar(final ClienteModel clienteModel) {

    return Optional.of(clienteModel)
      .map(model -> {
        this.regras.forEach(regra -> regra.executar(model));
        return model;
      })
      .map(this.repositorioSalvarCliente::salvar)
      .orElseThrow();
  }
}

