package com.techchallenge.devnet.application_business_rules.ports.entrada.produto;

import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;

public interface IProdutoCadastrarServicePort {

  ProdutoModel cadastrar(ProdutoModel produtoModel);
}

