package com.techchallenge.devnet.core.application.ports.saida.produto;

import com.techchallenge.devnet.core.domain.models.ProdutoModel;

public interface IProdutoSalvarRepositoryPort {

  ProdutoModel salvar(ProdutoModel produto);
}

