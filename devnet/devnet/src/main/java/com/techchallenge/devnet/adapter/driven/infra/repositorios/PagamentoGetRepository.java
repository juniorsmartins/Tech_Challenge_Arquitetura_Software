package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.PagamentoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IPagamentoRepository;
import com.techchallenge.devnet.core.domain.entities.Pagamento;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoFiltro;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PagamentoGetRepository implements IPagamentoRepository.GetRepository {

  @Autowired
  private PagamentoRepositoryJpa pagamentoRepositoryJpa;

  @Override
  public Page<Pagamento> pesquisar(final PagamentoFiltro filtro, final Pageable paginacao) {
    return this.pagamentoRepositoryJpa.findAll(PagamentoSpecification.consultarDinamicamente(filtro), paginacao);
  }

  @Override
  public Optional<Pagamento> consultarPorId(final Long id) {
    return this.pagamentoRepositoryJpa.findById(id);
  }
}

