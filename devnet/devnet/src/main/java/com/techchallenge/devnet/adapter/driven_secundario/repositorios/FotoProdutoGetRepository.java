package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.FotoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FotoProdutoGetRepository implements IFotoProdutoRepository.GetRepository {

  @Autowired
  private FotoProdutoRepositoryJpa fotoProdutoRepositoryJpa;

  @Override
  public Optional<FotoProduto> consultarPorId(final Long id) {
    return this.fotoProdutoRepositoryJpa.findById(id);
  }
}

