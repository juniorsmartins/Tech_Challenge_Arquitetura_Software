package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IProdutoService;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepository;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
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

