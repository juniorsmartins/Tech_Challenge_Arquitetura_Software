package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.ProdutoRepositoryQueries;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.FotoProduto;
import com.techchallenge.devnet.core.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoPostRepository implements IProdutoRepository.PostRepository, ProdutoRepositoryQueries {

  @Autowired
  private ProdutoRepositoryJpa jpa;

  @Override
  public Produto salvar(final Produto produto) {

    return this.jpa.save(produto);
  }

  @Override
  public FotoProduto save(FotoProduto fotoProduto) {

    return this.jpa.save(fotoProduto);
  }
}
