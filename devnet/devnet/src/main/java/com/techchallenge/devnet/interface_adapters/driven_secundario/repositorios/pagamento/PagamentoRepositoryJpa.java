package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.pagamento;

import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.PagamentoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PagamentoRepositoryJpa extends JpaRepository<PagamentoDao, Long>,
  JpaSpecificationExecutor<PagamentoDao> { }

