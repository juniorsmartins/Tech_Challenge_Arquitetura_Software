package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.foto;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.FotoProdutoDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.FotoProdutoApagarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class FotoProdutoDeleteRepositoryImpl implements FotoProdutoApagarRepositoryPort {

  private final AdapterSaida mapper;

  private final FotoProdutoRepositoryJpa jpa;

  public FotoProdutoDeleteRepositoryImpl(AdapterSaida mapper,
                                         FotoProdutoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final FotoProdutoModel fotoProdutoModel) {

    Optional.of(fotoProdutoModel)
      .map(model -> {
        var fotoProdutoEntity = this.mapper.converterOrigemParaDestino(model, FotoProdutoDao.class);
        this.jpa.delete(fotoProdutoEntity);
        return true;
      })
      .orElseThrow();
  }
}

