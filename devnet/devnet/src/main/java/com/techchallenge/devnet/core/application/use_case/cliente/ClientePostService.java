package com.techchallenge.devnet.core.application.use_case.cliente;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteCadastrarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasCliente;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostService implements IClienteCadastrarServicePort {

  private final IClienteSalvarRepositoryPort repositorioSalvarCliente;

  private final List<RegrasCliente> regras;

  public ClientePostService(IClienteSalvarRepositoryPort repositorioSalvarCliente,
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

