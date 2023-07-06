package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.PedidoFiltroDto;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.PedidoFiltro;
import com.techchallenge.devnet.core.domain.objects.specification.PedidoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoGetRepositoryAdapter implements IPedidoRepositoryPort.GetRepository {

  @Autowired
  private IMapper mapper;

  @Autowired
  private PedidoRepositoryJpa jpa;

  @Transactional(readOnly = true)
  @Override
  public Page<PedidoModel> pesquisar(final PedidoFiltro pedidoFiltro, final Pageable paginacao) {

    return Optional.of(pedidoFiltro)
      .map(filtro -> this.mapper.converterOrigemParaDestino(filtro, PedidoFiltroDto.class))
      .map(filtroDto -> this.jpa.findAll(PedidoSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, PedidoModel.class))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<PedidoModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, PedidoModel.class));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PedidoModel> consultarPorIdDeCliente(final Long clienteId) {

    return Optional.of(clienteId)
      .map(id -> this.jpa.findByClienteId(id))
      .map(entities -> this.mapper.converterListaOrigemParaListaDestino(entities, PedidoModel.class))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  @Override
  public List<PedidoModel> listar() {

    return Optional.of(this.jpa.findAll())
      .map(entities -> this.mapper.converterListaOrigemParaListaDestino(entities, PedidoModel.class))
      .orElseThrow();
  }
}

