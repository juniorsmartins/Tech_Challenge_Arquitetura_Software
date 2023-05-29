package com.techchallenge.devnet.adapter.driven.infra.repositories.jpa;

import com.techchallenge.devnet.core.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepositoryJpa extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> { }

