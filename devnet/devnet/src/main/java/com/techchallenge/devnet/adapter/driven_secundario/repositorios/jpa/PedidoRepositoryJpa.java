package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.core.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoRepositoryJpa extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> { }

