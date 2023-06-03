package com.techchallenge.devnet.core.application.use_case;

import com.fasterxml.jackson.core.type.TypeReference;
import com.techchallenge.devnet.adapter.driver.dtos.response.ClienteDtoResponse;
import com.techchallenge.devnet.adapter.driver.dtos.response.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.value_objects.ProdutoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoGetService implements IProdutoService.PesquisarService {

  @Autowired
  private IProdutoRepository.GetRepository repository;

  @Autowired
  private IMapper mapper;

  @Transactional(readOnly = true)
  @Override
  public Page<ProdutoDtoResponse> pesquisar(ProdutoFiltro filtro, Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repository.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaProdutos -> this.mapper.converterPaginaDeEntidadeParaPaginaDtoResponse(paginaProdutos, ProdutoDtoResponse.class))
      .orElseThrow();
  }
}

