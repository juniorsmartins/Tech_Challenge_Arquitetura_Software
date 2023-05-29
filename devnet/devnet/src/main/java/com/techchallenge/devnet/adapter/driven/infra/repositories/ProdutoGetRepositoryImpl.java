package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.Produto;
import com.techchallenge.devnet.core.domain.value_objects.ProdutoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProdutoGetRepositoryImpl implements IProdutoRepository.GetRepository {

  @Autowired
  private ProdutoRepositoryJpa repositoryJpa;

  @Override
  public Page<Produto> pesquisar(ProdutoFiltro filtro, Pageable paginacao) {

    return null;
  }

  @Override
  public Optional<Produto> consultarPorId(final Long id) {

    return this.repositoryJpa.findById(id);
  }
}

