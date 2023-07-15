package com.techchallenge.devnet.core.application.ports.saida.foto;

import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;

public interface IFotoProdutoApagarRepositoryPort {

  void deletar(FotoProdutoModel fotoProdutoModel);
}

