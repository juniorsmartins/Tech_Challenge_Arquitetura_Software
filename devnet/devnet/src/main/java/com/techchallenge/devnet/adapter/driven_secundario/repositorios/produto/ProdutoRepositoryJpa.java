package com.techchallenge.devnet.adapter.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Long>,
  JpaSpecificationExecutor<ProdutoEntity> {
}

