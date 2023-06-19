package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IFotoProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FotoProdutoGetService implements IFotoProdutoService.PesquisarService {

  @Autowired
  private IFotoProdutoRepository.GetRepository fotoProdutoGetRepository;

  @Override
  public FotoProdutoDtoResponse consultarPorId(final Long id) {

    return null;
  }
}

