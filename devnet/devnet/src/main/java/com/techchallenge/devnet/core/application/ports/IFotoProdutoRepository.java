package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.entities.FotoProduto;

import java.io.InputStream;

public interface IFotoProdutoRepository {

  interface PostRepository {
    FotoProduto salvar(FotoProduto fotoProduto, InputStream dadosArquivo);
  }
}

