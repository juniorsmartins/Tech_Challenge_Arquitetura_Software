package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.PagamentoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IPagamentoRepository;
import com.techchallenge.devnet.core.domain.entities.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoPostRepository implements IPagamentoRepository.PostRepository {

  @Autowired
  private PagamentoRepositoryJpa pagamentoRepositoryJpa;

  @Override
  public Pagamento salvar(Pagamento pagamento) {
    return this.pagamentoRepositoryJpa.save(pagamento);
  }
}

