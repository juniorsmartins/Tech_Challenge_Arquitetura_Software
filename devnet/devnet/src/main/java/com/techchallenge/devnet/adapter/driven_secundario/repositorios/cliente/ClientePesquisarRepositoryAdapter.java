package com.techchallenge.devnet.adapter.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.ClienteFiltroDto;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClientePesquisarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ClienteFiltro;
import com.techchallenge.devnet.core.domain.objects.specification.ClienteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClientePesquisarRepositoryAdapter implements IClientePesquisarRepositoryPort {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private ClienteRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Page<ClienteModel> pesquisar(final ClienteFiltro clienteFiltro, final Pageable paginacao) {

    return Optional.of(clienteFiltro)
      .map(filtro -> this.mapper.converterOrigemParaDestino(filtro, ClienteFiltroDto.class))
      .map(filtroDto -> this.jpa.findAll(ClienteSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, ClienteModel.class))
      .orElseThrow();
  }
}

