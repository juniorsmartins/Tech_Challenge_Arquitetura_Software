package com.techchallenge.devnet.adapter.driven_secundario.gateways.foto;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class FotoProdutoConsultarPorIdRepositoryAdapter implements IFotoProdutoConsultarPorIdRepositoryPort {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private FotoProdutoRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Optional<FotoProdutoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, FotoProdutoModel.class));
  }
}

