package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProdutoPutService implements IProdutoService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public ProdutoDtoResponse atualizar(final Long id, final ProdutoDtoRequest dtoRequest) {

    return this.produtoGetRepository.consultarPorId(id)
      .map(produto -> {
        BeanUtils.copyProperties(dtoRequest, produto, "id");
        return produto;
      })
      .map(produto -> this.mapper.converterEntidadeParaDtoResponse(produto, ProdutoDtoResponse.class))
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
        throw new ProdutoNaoEncontradoException(id);
      });
  }
}

