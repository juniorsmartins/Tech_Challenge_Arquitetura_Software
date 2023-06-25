package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.core.domain.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepositoryJpa extends JpaRepository<Email, Long> { }

