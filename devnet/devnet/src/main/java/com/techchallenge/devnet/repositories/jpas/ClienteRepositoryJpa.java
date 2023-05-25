package com.techchallenge.devnet.repositories.jpas;

import com.techchallenge.devnet.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositoryJpa extends JpaRepository<Cliente, Long> { }

