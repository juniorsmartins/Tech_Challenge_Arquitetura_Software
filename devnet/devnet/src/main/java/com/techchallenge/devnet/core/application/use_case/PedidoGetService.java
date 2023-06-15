package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.value_objects.specification.PedidoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoGetService implements IPedidoService.PesquisarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Transactional(readOnly = true)
  @Override
  public Page<PedidoDtoResponse> pesquisar(final PedidoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.pedidoGetRepository.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaPedidos -> this.mapper.converterPaginaDeEntidadeParaPaginaDtoResponse(paginaPedidos, PedidoDtoResponse.class))
      .orElseThrow();
  }
}

