package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IProdutoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoPutService implements IProdutoService.AtualizarService {

  @Autowired
  private IProdutoMapper mapper;

  @Autowired
  private IProdutoRepository.GetRepository repository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public ProdutoDtoResponse atualizar(final Long id, final ProdutoDtoRequest dtoRequest) {

    return this.repository.consultarPorId(id)
      .map(produto -> {
        BeanUtils.copyProperties(dtoRequest, produto, "id");
        return produto;
      })
      .map(produto -> this.mapper.converterEntidadeParaDtoResponse(produto))
      .orElseThrow();
  }
}

