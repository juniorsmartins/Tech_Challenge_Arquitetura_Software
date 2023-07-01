package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.FotoProdutoArquivo;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import com.techchallenge.devnet.core.domain.models.ImagemModel;

import java.io.IOException;

public interface IFotoProdutoServicePort {

  interface PutService {
    FotoProdutoModel inserirFotoNoProduto(Long id, FotoProdutoArquivo fotoProdutoArquivo) throws IOException;
  }

  interface GetService {
    FotoProdutoModel consultarPorId(Long id);

    ImagemModel consultarImagemPorId(Long id, String acceptHeader);
  }

  interface DeleteService {
    void deletarPorId(Long id);
  }
}

