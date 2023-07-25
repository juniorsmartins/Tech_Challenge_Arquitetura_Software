package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.frameworks_and_drivers.db.ProdutoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.ProdutoFiltroDto;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoPesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ProdutoFiltro;
import com.techchallenge.devnet.enterprise_business_rules.objects.specification.ProdutoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoGetRepositoryImpl implements IProdutoConsultarPorIdRepositoryPort, IProdutoPesquisarRepositoryPort {

  private final AdapterSaida mapper;

  private final ProdutoRepositoryJpa jpa;

  public ProdutoGetRepositoryImpl(AdapterSaida mapper,
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

