package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.PedidoRepositoryJpa;
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
}

