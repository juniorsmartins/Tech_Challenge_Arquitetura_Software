package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDeleteRepository implements IProdutoRepositoryPort.DeleteRepository {

  @Autowired
  private ProdutoRepositoryJpa repositoryJpa;

  @Override
  public void deletar(final ProdutoModel produto) {

    this.repositoryJpa.delete(produto);
  }
}

