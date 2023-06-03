package com.techchallenge.devnet.adapter.driven.infra.repositories;

import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoPostRepository implements IPedidoRepository.PostRepository {

  @Autowired
  private PedidoRepositoryJpa jpa;

  @Override
  public Pedido salvar(final Pedido pedido) {

    return this.jpa.save(pedido);
  }

  @Override
  public void flush() {

    this.jpa.flush();
  }
}

