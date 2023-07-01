package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.core.domain.models.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepositoryJpa extends JpaRepository<ItemPedido, Long> {

  List<ItemPedido> findByProdutoId(Long produtoId);
}

