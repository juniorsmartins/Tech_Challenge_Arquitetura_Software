package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.application.ports.entrada.IClienteServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepositoryPort;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasNegocioCliente;
import com.techchallenge.devnet.core.domain.entities.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostService implements IClienteServicePort.PostService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IClienteRepositoryPort.PostRepository clientePostRepository;

  @Autowired
  private List<RegrasNegocioCliente> regrasDeNegocio;

  @Transactional
  @Override
  public ClienteModel cadastrar(final ClienteModel clienteModel) {

    return Optional.of(clienteModel)
      .map(model -> {
        this.regrasDeNegocio.forEach(regra -> regra.executarRegrasDeNegocio(model));
        return model;
      })
      .map(this.clientePostRepository::salvar)
      .orElseThrow();
  }
}

