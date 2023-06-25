package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDeleteRepository implements IProdutoRepository.DeleteRepository {

  @Autowired
  private ProdutoRepositoryJpa repositoryJpa;

  @Override
  public void deletar(final Produto produto) {

    this.repositoryJpa.delete(produto);
  }
}

