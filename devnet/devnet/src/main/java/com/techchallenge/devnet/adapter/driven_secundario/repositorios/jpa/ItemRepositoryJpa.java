package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositoryJpa extends JpaRepository<ItemPedidoModel, Long> { }

