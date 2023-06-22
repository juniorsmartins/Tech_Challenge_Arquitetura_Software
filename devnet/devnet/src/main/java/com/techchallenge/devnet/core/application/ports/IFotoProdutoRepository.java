package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.entities.FotoProduto;

import java.io.InputStream;
import java.util.Optional;

public interface IFotoProdutoRepository {

  interface PostRepository {
    FotoProduto salvar(FotoProduto fotoProduto, InputStream dadosArquivo);

    void flush();
  }

  interface GetRepository {
    Optional<FotoProduto> consultarPorId(Long id);
  }

  interface DeleteRepository {
    void deletar(FotoProduto fotoProduto);
  }
}
