package com.techchallenge.devnet.application_business_rules.ports.saida.foto;

import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;

public interface FotoProdutoApagarRepositoryPort {

  void deletar(FotoProdutoModel fotoProdutoModel);
}

