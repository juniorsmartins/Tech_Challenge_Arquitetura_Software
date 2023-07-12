package com.techchallenge.devnet.adapter.driven_secundario.repositorios.email;

import com.techchallenge.devnet.adapter.driven_secundario.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepositoryJpa extends JpaRepository<EmailEntity, Long> {

  Optional<EmailEntity> findByEmailTo(String emailTo);
}

