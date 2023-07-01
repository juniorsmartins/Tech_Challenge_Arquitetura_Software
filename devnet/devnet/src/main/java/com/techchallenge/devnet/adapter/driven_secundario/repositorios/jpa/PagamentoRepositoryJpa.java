package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.adapter.driven_secundario.entities.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PagamentoRepositoryJpa extends JpaRepository<PagamentoEntity, Long>, JpaSpecificationExecutor<PagamentoEntity> { }

