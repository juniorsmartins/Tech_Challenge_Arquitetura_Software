package com.techchallenge.devnet.adapter.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoApagarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoDeleteRepository implements IProdutoApagarRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

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

