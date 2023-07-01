package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;

import java.io.InputStream;
import java.util.Optional;

public interface IFotoProdutoRepositoryPort {

  interface PostRepository {
    FotoProdutoModel salvar(FotoProdutoModel fotoProdutoModel, InputStream dadosArquivo);

    void flush();
  }

  interface GetRepository {
    Optional<FotoProdutoModel> consultarPorId(Long id);
  }

  interface DeleteRepository {
    void deletar(FotoProdutoModel fotoProdutoModel);
  }
}

