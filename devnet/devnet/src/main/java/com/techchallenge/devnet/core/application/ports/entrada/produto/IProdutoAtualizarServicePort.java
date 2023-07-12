package com.techchallenge.devnet.core.application.ports.entrada.produto;

import com.techchallenge.devnet.core.domain.models.ProdutoModel;

public interface IProdutoAtualizarServicePort {

  ProdutoModel atualizar(Long id, ProdutoModel produtoModel);
}

