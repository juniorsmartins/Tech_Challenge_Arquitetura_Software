package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.adapter.driven_secundario.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Long>,
  JpaSpecificationExecutor<ClienteEntity> {

  Optional<ClienteEntity> findByCpf(String cpf);
}

