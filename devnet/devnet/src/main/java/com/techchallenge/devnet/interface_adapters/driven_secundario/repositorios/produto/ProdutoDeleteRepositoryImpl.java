package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.frameworks_and_drivers.db.ProdutoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ProdutoDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoApagarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoDeleteRepositoryImpl implements IProdutoApagarRepositoryPort {

  private final AdapterSaida mapper;

  private final ProdutoRepositoryJpa jpa;

  public ProdutoDeleteRepositoryImpl(AdapterSaida mapper,
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

