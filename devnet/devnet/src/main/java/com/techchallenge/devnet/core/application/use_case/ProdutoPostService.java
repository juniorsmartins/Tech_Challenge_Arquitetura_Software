package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoPostService implements IProdutoService.CadastrarService {

  @Autowired
  private IProdutoMapper mapper;

  @Autowired
  private IProdutoRepository.PostRepository repository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public ProdutoDtoResponse cadastrar(final ProdutoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto))
      .map(produto -> this.repository.salvar(produto))
      .map(produto -> this.mapper.converterEntidadeParaDtoResponse(produto))
      .orElseThrow();
  }
}

