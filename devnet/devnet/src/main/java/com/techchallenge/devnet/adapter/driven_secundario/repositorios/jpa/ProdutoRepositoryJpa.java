package com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa;

import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import com.techchallenge.devnet.core.domain.models.FotoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoEntity, Long>, JpaSpecificationExecutor<ProdutoEntity> {

  @Query("SELECT f FROM FotoProduto f JOIN Produto p WHERE f.id = :produtoId and p.id = :produtoId")
  Optional<FotoProduto> buscarFotoProdutoPorId(Long produtoId);
}

