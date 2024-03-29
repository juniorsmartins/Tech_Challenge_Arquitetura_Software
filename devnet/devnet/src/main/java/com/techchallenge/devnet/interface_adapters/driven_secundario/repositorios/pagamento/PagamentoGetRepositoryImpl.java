package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.pagamento;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.PagamentoFiltroDto;
import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.PagamentoConsultarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.PagamentoPesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PagamentoFiltro;
import com.techchallenge.devnet.enterprise_business_rules.objects.specification.PagamentoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class PagamentoGetRepositoryImpl implements PagamentoPesquisarRepositoryPort, PagamentoConsultarRepositoryPort {

  private final AdapterSaida mapper;

  private final PagamentoRepositoryJpa jpa;

  public PagamentoGetRepositoryImpl(AdapterSaida mapper,
                                    PagamentoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional(readOnly = true)
  @Override
  public Page<PagamentoModel> pesquisar(final PagamentoFiltro pagamentoFiltro, final Pageable paginacao) {

    return Optional.of(pagamentoFiltro)
      .map(filtro -> this.mapper.converterOrigemParaDestino(filtro, PagamentoFiltroDto.class))
      .map(filtroDto -> this.jpa.findAll(PagamentoSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, PagamentoModel.class))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<PagamentoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, PagamentoModel.class));
  }
}

