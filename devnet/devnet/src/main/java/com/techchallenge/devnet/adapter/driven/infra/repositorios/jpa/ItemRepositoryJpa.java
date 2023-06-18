package com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa;

import com.techchallenge.devnet.core.domain.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositoryJpa extends JpaRepository<ItemPedido, Long> { }

