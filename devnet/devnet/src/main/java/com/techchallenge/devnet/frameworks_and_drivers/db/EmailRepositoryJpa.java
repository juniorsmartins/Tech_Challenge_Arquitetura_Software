package com.techchallenge.devnet.frameworks_and_drivers.db;

import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.EmailDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepositoryJpa extends JpaRepository<EmailDao, Long> {

  Optional<EmailDao> findByEmailTo(String emailTo);
}

