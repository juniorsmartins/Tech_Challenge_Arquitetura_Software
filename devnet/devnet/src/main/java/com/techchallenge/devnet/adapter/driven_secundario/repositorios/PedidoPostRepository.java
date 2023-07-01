package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoPostRepository implements IPedidoRepositoryPort.PostRepository {

  @Autowired
  private PedidoRepositoryJpa jpa;

  @Override
  public PedidoEntity salvar(final PedidoEntity pedido) {

    return this.jpa.save(pedido);
  }
}

