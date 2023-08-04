package com.techchallenge.devnet.application_business_rules.ports.saida.produto;

import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;

public interface ProdutoApagarRepositoryPort {
  void deletar(ProdutoModel produto);
}

