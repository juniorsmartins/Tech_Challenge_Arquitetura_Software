package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoDeleteRepositoryAdapter implements IProdutoRepositoryPort.DeleteRepository {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private ProdutoRepositoryJpa jpa;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final ProdutoModel produtoModel) {

    Optional.of(produtoModel)
      .map(model -> {
        var produtoEntity = this.mapper.converterOrigemParaDestino(model, ProdutoEntity.class);
        this.jpa.delete(produtoEntity);
        return true;
      })
      .orElseThrow();
  }
}

