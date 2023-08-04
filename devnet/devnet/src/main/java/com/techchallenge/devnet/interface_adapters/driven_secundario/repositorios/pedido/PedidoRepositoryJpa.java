package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.pedido;

import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.PedidoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PedidoRepositoryJpa extends JpaRepository<PedidoDao, Long>,
  JpaSpecificationExecutor<PedidoDao> {

  List<PedidoDao> findByClienteId(Long clienteId);
}

