package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.cliente;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.ClienteFiltroDto;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteConsultarPorCpfRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClientePesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ClienteFiltro;
import com.techchallenge.devnet.enterprise_business_rules.objects.specification.ClienteSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClienteGetRepositoryImpl implements ClientePesquisarRepositoryPort,
  ClienteConsultarPorIdRepositoryPort, ClienteConsultarPorCpfRepositoryPort {

  private final AdapterSaida mapper;

  private final ClienteRepositoryJpa jpa;

  public ClienteGetRepositoryImpl(AdapterSaida mapper,
                                  ClienteRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<ClienteModel> consultarPorId(final Long id) {

    return this.jpa.findById(id)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ClienteModel.class));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ClienteModel> pesquisar(final ClienteFiltro clienteFiltro, final Pageable paginacao) {

    return Optional.of(clienteFiltro)
      .map(filtro -> this.mapper.converterOrigemParaDestino(filtro, ClienteFiltroDto.class))
      .map(filtroDto -> this.jpa.findAll(ClienteSpecification.consultarDinamicamente(filtroDto), paginacao))
      .map(paginaEntity -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaEntity, ClienteModel.class))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<ClienteModel> consultarPorCpf(final String cpf) {

    return this.jpa.findByCpf(cpf)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ClienteModel.class));
  }
}

