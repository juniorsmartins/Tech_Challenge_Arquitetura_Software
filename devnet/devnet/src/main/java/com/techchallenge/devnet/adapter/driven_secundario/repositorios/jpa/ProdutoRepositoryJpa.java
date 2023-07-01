package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.adapter.driven_secundario.entities.FotoProdutoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Long>,
  JpaSpecificationExecutor<ProdutoEntity> {

  @Query("SELECT f FROM FotoProdutoEntity f JOIN ProdutoEntity p WHERE f.id = :produtoId and p.id = :produtoId")
  Optional<FotoProdutoEntity> buscarFotoProdutoPorId(Long produtoId);
}

