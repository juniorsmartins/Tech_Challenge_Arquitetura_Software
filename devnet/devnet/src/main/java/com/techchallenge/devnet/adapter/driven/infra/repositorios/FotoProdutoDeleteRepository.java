package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IFotoProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.FotoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FotoProdutoDeleteRepository implements IFotoProdutoRepository.DeleteRepository {

  @Autowired
  private FotoProdutoRepositoryJpa fotoProdutoRepositoryJpa;

  @Override
  public void deletar(final FotoProduto fotoProduto) {
    this.fotoProdutoRepositoryJpa.delete(fotoProduto);
  }
}

