package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.FotoProdutoDtoRequest;

public interface IFotoProdutoService {

  interface AtualizarService {
    void inserirFotoNoProduto(Long id, FotoProdutoDtoRequest fotoProdutoDtoRequest);
  }
}

