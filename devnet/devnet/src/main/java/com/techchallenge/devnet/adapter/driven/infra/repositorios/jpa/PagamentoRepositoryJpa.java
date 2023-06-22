package com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa;

import com.techchallenge.devnet.core.domain.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepositoryJpa extends JpaRepository<Pagamento, Long> { }
