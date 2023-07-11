package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClientePesquisarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClientePesquisarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ClienteFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientePesquisarService implements IClientePesquisarServicePort {

  @Autowired
  private IClientePesquisarRepositoryPort clientePesquisarRepository;

  @Override
  public Page<ClienteModel> pesquisar(final ClienteFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.clientePesquisarRepository.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }
}

