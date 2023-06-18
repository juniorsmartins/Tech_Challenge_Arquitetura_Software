package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoPostRepository implements IProdutoRepository.PostRepository {

  @Autowired
  private ProdutoRepositoryJpa produtoRepositoryJpa;

  @Override
  public Produto salvar(final Produto produto) {

    return this.produtoRepositoryJpa.save(produto);
  }
}

