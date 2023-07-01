package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ProdutoFiltro;
import com.techchallenge.devnet.core.domain.value_objects.specification.ProdutoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProdutoGetRepository implements IProdutoRepositoryPort.GetRepository {

  @Autowired
  private ProdutoRepositoryJpa repositoryJpa;

  @Override
  public Page<ProdutoModel> pesquisar(final ProdutoFiltro filtro, final Pageable paginacao) {

    return this.repositoryJpa.findAll(ProdutoSpecification.consultarDinamicamente(filtro), paginacao);
  }

  @Override
  public Optional<ProdutoModel> consultarPorId(final Long id) {

    return this.repositoryJpa.findById(id);
  }
}

