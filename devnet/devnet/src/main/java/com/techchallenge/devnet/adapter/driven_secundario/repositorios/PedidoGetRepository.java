package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepository;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PedidoFiltro;
import com.techchallenge.devnet.core.domain.value_objects.specification.PedidoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoGetRepository implements IPedidoRepository.GetRepository {

  @Autowired
  private PedidoRepositoryJpa repositoryJpa;

  @Override
  public Page<Pedido> pesquisar(final PedidoFiltro filtro, final Pageable paginacao) {
    return this.repositoryJpa.findAll(PedidoSpecification.consultarDinamicamente(filtro), paginacao);
  }

  @Override
  public Optional<Pedido> consultarPorId(final Long id) {
    return this.repositoryJpa.findById(id);
  }

  @Override
  public List<Pedido> consultarPorIdDeCliente(final Long clienteId) {
    return this.repositoryJpa.findByClienteId(clienteId);
  }

  @Override
  public List<Pedido> listar() {
    return this.repositoryJpa.findAll();
  }
}

