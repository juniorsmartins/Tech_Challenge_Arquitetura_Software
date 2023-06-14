package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoPostService implements IProdutoService.CadastrarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IProdutoRepository.PostRepository produtoPostRepository;

  @Transactional
  @Override
  public ProdutoDtoResponse cadastrar(final ProdutoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Produto.class))
      .map(this.produtoPostRepository::salvar)
      .map(produto -> this.mapper.converterEntidadeParaDtoResponse(produto, ProdutoDtoResponse.class))
      .orElseThrow();
  }
}

