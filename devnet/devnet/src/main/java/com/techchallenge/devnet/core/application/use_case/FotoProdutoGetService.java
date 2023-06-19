package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IFotoProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoGetService implements IFotoProdutoService.PesquisarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IFotoProdutoRepository.GetRepository fotoProdutoGetRepository;

  @Transactional(readOnly = true)
  @Override
  public FotoProdutoDtoResponse consultarPorId(final Long id) {

    return this.fotoProdutoGetRepository.consultarPorId(id)
      .map(fotoProduto -> this.mapper.converterEntidadeParaDtoResponse(fotoProduto, FotoProdutoDtoResponse.class))
      .orElseThrow(() -> new FotoProdutoNaoEncontradoException(id));
  }
}

