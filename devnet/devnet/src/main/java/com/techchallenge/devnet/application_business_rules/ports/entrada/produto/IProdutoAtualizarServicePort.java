package com.techchallenge.devnet.application_business_rules.ports.entrada.produto;

import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;

public interface IProdutoAtualizarServicePort {

  ProdutoModel atualizar(Long id, ProdutoModel produtoModel);
}

