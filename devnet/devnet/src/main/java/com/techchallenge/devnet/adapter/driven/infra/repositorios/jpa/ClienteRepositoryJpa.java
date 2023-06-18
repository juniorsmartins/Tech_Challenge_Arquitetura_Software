package com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa;

import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClienteRepositoryJpa extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

  Optional<Cliente> findByCpf(String cpf);
}

