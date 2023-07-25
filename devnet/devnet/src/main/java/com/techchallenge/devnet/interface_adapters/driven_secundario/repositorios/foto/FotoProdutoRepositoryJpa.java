package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.foto;

import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.FotoProdutoDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoProdutoRepositoryJpa extends JpaRepository<FotoProdutoDao, Long> { }

