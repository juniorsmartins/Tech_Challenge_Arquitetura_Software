package com.techchallenge.devnet.repositories.jpas;

import com.techchallenge.devnet.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepositoryJpa extends JpaRepository<Cliente, Long> {

  Optional<Cliente> findByCodigo(UUID codigo);
}
