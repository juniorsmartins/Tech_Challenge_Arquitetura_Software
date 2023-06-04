package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoPostRepository implements IProdutoRepository.PostRepository {

  @Autowired
  private ProdutoRepositoryJpa jpa;

  @Override
  public Produto salvar(final Produto produto) {

    return this.jpa.save(produto);
  }
}
