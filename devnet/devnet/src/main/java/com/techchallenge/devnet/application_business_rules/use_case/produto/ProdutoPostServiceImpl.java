package com.techchallenge.devnet.application_business_rules.use_case.produto;

import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.ProdutoCadastrarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.ProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoPostServiceImpl implements ProdutoCadastrarServicePort {

  private final ProdutoSalvarRepositoryPort repositorioSalvarProduto;

  public ProdutoPostServiceImpl(ProdutoSalvarRepositoryPort repositorioSalvarProduto) {
    this.repositorioSalvarProduto = repositorioSalvarProduto;
  }

  @Override
  public ProdutoModel cadastrar(final ProdutoModel produtoModel) {

    return Optional.of(produtoModel)
      .map(this.repositorioSalvarProduto::salvar)
      .orElseThrow();
  }
}

