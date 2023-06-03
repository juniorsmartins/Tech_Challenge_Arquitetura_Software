package com.techchallenge.devnet.core.application.use_case;

import com.fasterxml.jackson.core.type.TypeReference;
import com.techchallenge.devnet.adapter.driver.dtos.request.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoPutService implements IProdutoService.AtualizarService {

  @Autowired
  private IMapper mapper;

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
      .map(produto -> this.mapper.converterEntidadeParaDtoResponse(produto, ProdutoDtoResponse.class))
      .orElseThrow();
  }
}

