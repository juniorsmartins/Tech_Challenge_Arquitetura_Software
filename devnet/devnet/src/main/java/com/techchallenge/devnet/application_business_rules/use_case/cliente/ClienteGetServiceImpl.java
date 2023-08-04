package com.techchallenge.devnet.application_business_rules.use_case.cliente;

import com.techchallenge.devnet.application_business_rules.ports.entrada.cliente.ClientePesquisarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClientePesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteGetServiceImpl implements ClientePesquisarServicePort {

  private final ClientePesquisarRepositoryPort repositorioPesquisarCliente;

  public ClienteGetServiceImpl(ClientePesquisarRepositoryPort repositorioPesquisarCliente) {
    this.repositorioPesquisarCliente = repositorioPesquisarCliente;
  }

  @Override
  public Page<ClienteModel> pesquisar(final ClienteFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repositorioPesquisarCliente.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }
}

