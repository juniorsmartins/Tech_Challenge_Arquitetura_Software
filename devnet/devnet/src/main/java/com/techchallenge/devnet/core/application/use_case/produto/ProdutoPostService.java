package com.techchallenge.devnet.core.application.use_case.produto;

import com.techchallenge.devnet.core.application.ports.entrada.produto.IProdutoCadastrarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
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

