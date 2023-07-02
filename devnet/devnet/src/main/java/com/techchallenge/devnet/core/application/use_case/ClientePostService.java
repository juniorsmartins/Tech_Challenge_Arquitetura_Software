package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IClienteServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepositoryPort;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasCliente;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostService implements IClienteServicePort.PostService {

  @Autowired
  private IClienteRepositoryPort.PostRepository clientePostRepository;

  @Autowired
  private List<RegrasCliente> regras;

  @Override
  public ClienteModel cadastrar(final ClienteModel clienteModel) {

    return Optional.of(clienteModel)
      .map(model -> {
        this.regras.forEach(regra -> regra.executar(model));
        return model;
      })
      .map(this.clientePostRepository::salvar)
      .orElseThrow();
  }
}

