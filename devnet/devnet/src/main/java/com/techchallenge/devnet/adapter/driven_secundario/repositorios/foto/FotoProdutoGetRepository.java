package com.techchallenge.devnet.adapter.driven_secundario.repositorios.foto;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class FotoProdutoGetRepository implements IFotoProdutoConsultarPorIdRepositoryPort {

  private final IAdapterSaida mapper;

  private final FotoProdutoRepositoryJpa jpa;

  public FotoProdutoGetRepository(IAdapterSaida mapper,
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

