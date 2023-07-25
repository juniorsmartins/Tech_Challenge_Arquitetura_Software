package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.pedido;

import com.techchallenge.devnet.frameworks_and_drivers.db.PedidoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.PedidoFiltroDto;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoBuscarPorIdClienteRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoListarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoPesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PedidoFiltro;
import com.techchallenge.devnet.enterprise_business_rules.objects.specification.PedidoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoGetRepositoryImpl implements IPedidoPesquisarRepositoryPort, IPedidoConsultarPorIdRepositoryPort,
  IPedidoListarRepositoryPort, IPedidoBuscarPorIdClienteRepositoryPort {

  private final AdapterSaida mapper;

  private final PedidoRepositoryJpa jpa;

  public PedidoGetRepositoryImpl(AdapterSaida mapper,
                                 PedidoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

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
  public List<PedidoModel> buscarPorIdDeCliente(final Long clienteId) {

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

