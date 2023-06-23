package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPagamentoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PagamentoGetService implements IPagamentoService.PesquisarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPagamentoRepository.GetRepository pagamentoGetRepository;

  @Transactional(readOnly = true)
  @Override
  public Page<PagamentoDtoResponse> pesquisar(final PagamentoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.pagamentoGetRepository.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaPagamentos -> this.mapper.converterPaginaDeEntidadeParaPaginaDtoResponse(paginaPagamentos, PagamentoDtoResponse.class))
      .orElseThrow();
  }
}

