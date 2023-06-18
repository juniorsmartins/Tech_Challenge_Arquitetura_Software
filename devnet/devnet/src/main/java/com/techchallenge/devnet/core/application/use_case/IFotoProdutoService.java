package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.requisicao.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.FotoProdutoDtoResponse;

public interface IFotoProdutoService {

  interface AtualizarService {
    FotoProdutoDtoResponse inserirFotoNoProduto(Long id, FotoProdutoDtoRequest fotoProdutoDtoRequest);
  }
}

