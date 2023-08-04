package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ClienteDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClienteRepositoryJpa extends JpaRepository<ClienteDao, Long>,
  JpaSpecificationExecutor<ClienteDao> {

  Optional<ClienteDao> findByCpf(String cpf);
}

