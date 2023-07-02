package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.entities.FotoProdutoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class FotoProdutoDeleteRepositoryAdapter implements IFotoProdutoRepositoryPort.DeleteRepository {

  @Autowired
  private IMapper mapper;

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

