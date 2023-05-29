package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.entities.Produto;

public interface IProdutoRepository {

  interface PostRepository {
    Produto salvar(Produto produto);
  }
}

