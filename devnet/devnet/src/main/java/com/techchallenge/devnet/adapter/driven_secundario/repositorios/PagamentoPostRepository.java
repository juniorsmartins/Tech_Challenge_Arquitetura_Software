package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PagamentoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoPostRepository implements IPagamentoRepositoryPort.PostRepository {

  @Autowired
  private PagamentoRepositoryJpa pagamentoRepositoryJpa;

  @Override
  public PagamentoModel salvar(PagamentoModel pagamento) {
    return this.pagamentoRepositoryJpa.save(pagamento);
  }
}

