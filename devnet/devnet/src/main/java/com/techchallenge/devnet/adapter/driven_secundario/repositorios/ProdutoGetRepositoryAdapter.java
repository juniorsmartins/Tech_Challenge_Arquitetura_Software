package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.conversor_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.ProdutoFiltroDto;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ProdutoFiltro;
import com.techchallenge.devnet.core.domain.objects.specification.ProdutoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoGetRepositoryAdapter implements IProdutoRepositoryPort.GetRepository {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private ProdutoRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Page<ProdutoModel> pesquisar(final ProdutoFiltro produtoFiltro, final Pageable paginacao) {

    return Optional.of(produtoFiltro)
      .map(filtro -> this.mapper.converterOrigemParaDestino(filtro, ProdutoFiltroDto.class))
      .map(filtroDto -> this.jpa.findAll(ProdutoSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, ProdutoModel.class))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<ProdutoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ProdutoModel.class));
  }
}

