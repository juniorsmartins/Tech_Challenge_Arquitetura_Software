package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import com.techchallenge.devnet.core.domain.models.ImagemModel;

public interface IFotoProdutoServicePort {

  interface GetService {
    FotoProdutoModel consultarPorId(Long id);

    ImagemModel consultarImagemPorId(Long id, String acceptHeader);
  }

  interface DeleteService {
    void deletarPorId(Long id);
  }
}

