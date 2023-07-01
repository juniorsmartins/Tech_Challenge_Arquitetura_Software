package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.core.domain.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PedidoRepositoryJpa extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

  List<Pedido> findByClienteId(Long clienteId);
}

