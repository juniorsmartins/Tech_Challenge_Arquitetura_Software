package com.techchallenge.devnet.application_business_rules.use_case.produto;

import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.IProdutoCadastrarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoPostService implements IProdutoCadastrarServicePort {

  private final IProdutoSalvarRepositoryPort repositorioSalvarProduto;

  public ProdutoPostService(IProdutoSalvarRepositoryPort repositorioSalvarProduto) {
    this.repositorioSalvarProduto = repositorioSalvarProduto;
  }

  @Override
  public ProdutoModel cadastrar(final ProdutoModel produtoModel) {

    return Optional.of(produtoModel)
      .map(this.repositorioSalvarProduto::salvar)
      .orElseThrow();
  }
}

