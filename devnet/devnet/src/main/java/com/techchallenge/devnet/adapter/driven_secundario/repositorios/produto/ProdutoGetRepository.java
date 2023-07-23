package com.techchallenge.devnet.adapter.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driver_primario.filtros.ProdutoFiltroDto;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoPesquisarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ProdutoFiltro;
import com.techchallenge.devnet.core.domain.objects.specification.ProdutoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoGetRepository implements IProdutoConsultarPorIdRepositoryPort, IProdutoPesquisarRepositoryPort {

  private final IAdapterSaida mapper;

  private final ProdutoRepositoryJpa jpa;

  public ProdutoGetRepository(IAdapterSaida mapper,
                              ProdutoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<ProdutoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ProdutoModel.class));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ProdutoModel> pesquisar(final ProdutoFiltro produtoFiltro, final Pageable paginacao) {

    return Optional.of(produtoFiltro)
      .map(filtro -> this.mapper.converterOrigemParaDestino(filtro, ProdutoFiltroDto.class))
      .map(filtroDto -> this.jpa.findAll(ProdutoSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, ProdutoModel.class))
      .orElseThrow();
  }
}

