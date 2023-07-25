package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ProdutoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoDao, Long>,
  JpaSpecificationExecutor<ProdutoDao> {
}

