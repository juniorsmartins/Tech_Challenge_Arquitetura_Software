package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.entities.Produto;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ProdutoFiltro;
import com.techchallenge.devnet.core.domain.value_objects.specification.ProdutoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProdutoGetRepository implements IProdutoRepository.GetRepository {

  @Autowired
  private ProdutoRepositoryJpa repositoryJpa;

  @Override
  public Page<Produto> pesquisar(final ProdutoFiltro filtro, final Pageable paginacao) {

    return this.repositoryJpa.findAll(ProdutoSpecification.consultarDinamicamente(filtro), paginacao);
  }

  @Override
  public Optional<Produto> consultarPorId(final Long id) {

    return this.repositoryJpa.findById(id);
  }
}

