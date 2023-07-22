package com.techchallenge.devnet.adapter.driven_secundario.repositorios.item_pedido;

import com.techchallenge.devnet.adapter.driven_secundario.entities.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepositoryJpa extends JpaRepository<ItemPedidoEntity, Long> {

  List<ItemPedidoEntity> findByProdutoId(Long produtoId);
}

