package com.techchallenge.devnet.adapter.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.daos.ProdutoDao;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoApagarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoDeleteRepository implements IProdutoApagarRepositoryPort {

  private final IAdapterSaida mapper;

  private final ProdutoRepositoryJpa jpa;

  public ProdutoDeleteRepository(IAdapterSaida mapper,
                                 ProdutoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final ProdutoModel produtoModel) {

    Optional.of(produtoModel)
      .map(model -> {
        var produtoEntity = this.mapper.converterOrigemParaDestino(model, ProdutoDao.class);
        this.jpa.delete(produtoEntity);
        return true;
      })
      .orElseThrow();
  }
}

