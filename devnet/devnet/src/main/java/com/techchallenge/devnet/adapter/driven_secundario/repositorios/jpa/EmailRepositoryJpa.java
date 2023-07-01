package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.adapter.driven_secundario.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepositoryJpa extends JpaRepository<EmailEntity, Long> { }

