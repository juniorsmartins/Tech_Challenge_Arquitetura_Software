package com.techchallenge.devnet.adapter.driven_secundario.gateways.jpa;

import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PedidoRepositoryJpa extends JpaRepository<PedidoEntity, Long>,
  JpaSpecificationExecutor<PedidoEntity> {

  List<PedidoEntity> findByClienteId(Long clienteId);
}

