package com.techchallenge.devnet.adapter.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoPostRepository implements IProdutoSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final ProdutoRepositoryJpa jpa;

  public ProdutoPostRepository(IAdapterSaida mapper,
                               ProdutoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional
  @Override
  public ProdutoModel salvar(final ProdutoModel produtoModel) {

    return Optional.of(produtoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ProdutoEntity.class))
      .map(this.jpa::saveAndFlush)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ProdutoModel.class))
      .orElseThrow();
  }
}

