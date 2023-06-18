package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IFotoProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.FotoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FotoProdutoPostRepository implements IFotoProdutoRepository.PostRepository {
  @Autowired
  private FotoProdutoRepositoryJpa fotoProdutoRepositoryJpa;

  @Override
  public FotoProduto salvar(final FotoProduto fotoProduto) {

    var produtoId = fotoProduto.getProduto().getId();
    var fotoExistente = this.fotoProdutoRepositoryJpa.findById(produtoId);
    if (fotoExistente.isPresent()) {
      this.fotoProdutoRepositoryJpa.delete(fotoExistente.get());
    }

    return this.fotoProdutoRepositoryJpa.save(fotoProduto);
  }
}
