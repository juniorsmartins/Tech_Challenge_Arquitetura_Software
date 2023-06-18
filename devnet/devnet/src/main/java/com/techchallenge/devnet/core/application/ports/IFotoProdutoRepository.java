package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.entities.FotoProduto;

public interface IFotoProdutoRepository {

  interface PostRepository {
    FotoProduto salvar(FotoProduto fotoProduto);
  }
}

