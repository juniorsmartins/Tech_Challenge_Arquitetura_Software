package com.techchallenge.devnet.core.application.use_case.cliente;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClientePesquisarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClientePesquisarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteGetService implements IClientePesquisarServicePort {

  private final IClientePesquisarRepositoryPort repositorioPesquisarCliente;

  public ClienteGetService(IClientePesquisarRepositoryPort repositorioPesquisarCliente) {
    this.repositorioPesquisarCliente = repositorioPesquisarCliente;
  }

  @Override
  public Page<ClienteModel> pesquisar(final ClienteFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repositorioPesquisarCliente.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }
}

