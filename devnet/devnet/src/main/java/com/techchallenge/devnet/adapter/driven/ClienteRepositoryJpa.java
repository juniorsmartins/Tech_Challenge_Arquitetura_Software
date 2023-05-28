package com.techchallenge.devnet.adapter.driven;

import com.techchallenge.devnet.core.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepositoryJpa extends JpaRepository<Cliente, Long> {

  Optional<Cliente> findByCpf(String cpf);
}

