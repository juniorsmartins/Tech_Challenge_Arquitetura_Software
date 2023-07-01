package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PagamentoRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.PagamentoFiltroDto;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PagamentoFiltro;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PagamentoGetRepositoryAdapter implements IPagamentoRepositoryPort.GetRepository {

  @Autowired
  private IMapper mapper;

  @Autowired
  private PagamentoRepositoryJpa jpa;

  @Override
  public Page<PagamentoModel> pesquisar(final PagamentoFiltro pagamentoFiltro, final Pageable paginacao) {

    return Optional.of(pagamentoFiltro)
      .map(filtro -> this.mapper.converterOrigemParaDestino(filtro, PagamentoFiltroDto.class))
      .map(filtroDto -> this.jpa.findAll(PagamentoSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, PagamentoModel.class))
      .orElseThrow();
  }

  @Override
  public Optional<PagamentoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, PagamentoModel.class));
  }
}

