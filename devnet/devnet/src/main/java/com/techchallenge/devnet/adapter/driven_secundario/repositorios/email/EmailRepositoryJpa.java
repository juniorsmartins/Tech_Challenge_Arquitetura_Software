package com.techchallenge.devnet.adapter.driven_secundario.repositorios.email;

import com.techchallenge.devnet.adapter.driven_secundario.daos.EmailDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepositoryJpa extends JpaRepository<EmailDao, Long> {

  Optional<EmailDao> findByEmailTo(String emailTo);
}

