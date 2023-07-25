package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.produto;

import com.techchallenge.devnet.frameworks_and_drivers.db.ProdutoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ProdutoDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ProdutoPostRepositoryImpl implements IProdutoSalvarRepositoryPort {

  private final AdapterSaida mapper;

  private final ProdutoRepositoryJpa jpa;

  public ProdutoPostRepositoryImpl(AdapterSaida mapper,
                                   ProdutoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional
  @Override
  public ProdutoModel salvar(final ProdutoModel produtoModel) {

    return Optional.of(produtoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ProdutoDao.class))
      .map(this.jpa::saveAndFlush)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ProdutoModel.class))
      .orElseThrow();
  }
}

