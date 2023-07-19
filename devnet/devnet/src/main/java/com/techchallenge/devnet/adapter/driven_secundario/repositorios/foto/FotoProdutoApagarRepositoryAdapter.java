package com.techchallenge.devnet.adapter.driven_secundario.repositorios.foto;

import com.techchallenge.devnet.adapter.driven_secundario.adapters_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.FotoProdutoEntity;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoApagarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class FotoProdutoApagarRepositoryAdapter implements IFotoProdutoApagarRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

  @Autowired
  private FotoProdutoRepositoryJpa jpa;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final FotoProdutoModel fotoProdutoModel) {

    Optional.of(fotoProdutoModel)
      .map(model -> {
        var fotoProdutoEntity = this.mapper.converterOrigemParaDestino(model, FotoProdutoEntity.class);
        this.jpa.delete(fotoProdutoEntity);
        return true;
      })
      .orElseThrow();
  }
}

