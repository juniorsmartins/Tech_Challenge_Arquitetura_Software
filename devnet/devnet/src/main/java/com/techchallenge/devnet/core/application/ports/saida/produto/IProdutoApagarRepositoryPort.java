package com.techchallenge.devnet.core.application.ports.saida.produto;

import com.techchallenge.devnet.core.domain.models.ProdutoModel;

public interface IProdutoApagarRepositoryPort {
  void deletar(ProdutoModel produto);
}

