package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.item_pedido;

import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ItemPedidoDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepositoryJpa extends JpaRepository<ItemPedidoDao, Long> {

  List<ItemPedidoDao> findByProdutoId(Long produtoId);
}

