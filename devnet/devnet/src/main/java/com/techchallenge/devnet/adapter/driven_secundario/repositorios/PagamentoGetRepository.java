package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PagamentoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PagamentoFiltro;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PagamentoGetRepository implements IPagamentoRepositoryPort.GetRepository {

  @Autowired
  private PagamentoRepositoryJpa pagamentoRepositoryJpa;

  @Override
  public Page<PagamentoModel> pesquisar(final PagamentoFiltro filtro, final Pageable paginacao) {
    return this.pagamentoRepositoryJpa.findAll(PagamentoSpecification.consultarDinamicamente(filtro), paginacao);
  }

  @Override
  public Optional<PagamentoModel> consultarPorId(final Long id) {
    return this.pagamentoRepositoryJpa.findById(id);
  }
}

