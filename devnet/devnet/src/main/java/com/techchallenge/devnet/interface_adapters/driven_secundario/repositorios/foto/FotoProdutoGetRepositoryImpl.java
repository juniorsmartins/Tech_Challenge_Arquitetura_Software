package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.foto;

import com.techchallenge.devnet.frameworks_and_drivers.db.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class FotoProdutoGetRepositoryImpl implements IFotoProdutoConsultarPorIdRepositoryPort {

  private final AdapterSaida mapper;

  private final FotoProdutoRepositoryJpa jpa;

  public FotoProdutoGetRepositoryImpl(AdapterSaida mapper,
                                      FotoProdutoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<FotoProdutoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, FotoProdutoModel.class));
  }
}

