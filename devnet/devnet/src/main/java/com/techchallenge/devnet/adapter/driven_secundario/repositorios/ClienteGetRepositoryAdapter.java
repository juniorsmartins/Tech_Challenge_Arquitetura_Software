package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.ClienteFiltroDto;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepositoryPort;
import com.techchallenge.devnet.core.domain.entities.ClienteModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ClienteFiltro;
import com.techchallenge.devnet.core.domain.value_objects.specification.ClienteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteGetRepositoryAdapter implements IClienteRepositoryPort.GetRepository {

  @Autowired
  private IMapper mapper;

  @Autowired
  private ClienteRepositoryJpa repositoryJpa;

  @Override
  public Page<ClienteModel> pesquisar(final ClienteFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(clienteFiltro -> this.mapper.converterOrigemParaDestino(clienteFiltro, ClienteFiltroDto.class))
      .map(filtroDto -> this.repositoryJpa.findAll(ClienteSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, ClienteModel.class))
      .orElseThrow();
  }

  @Override
  public Optional<ClienteModel> consultarPorId(final Long id) {

    return this.repositoryJpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ClienteModel.class));
  }

  @Override
  public Optional<ClienteModel> consultarPorCpf(final String cpf) {

    return this.repositoryJpa.findByCpf(cpf)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ClienteModel.class));
  }
}

