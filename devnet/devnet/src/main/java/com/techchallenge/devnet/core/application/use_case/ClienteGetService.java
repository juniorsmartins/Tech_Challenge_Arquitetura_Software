package com.techchallenge.devnet.core.application.use_case;

import com.fasterxml.jackson.core.type.TypeReference;
import com.techchallenge.devnet.adapter.driver.dtos.response.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.value_objects.ClienteFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteGetService implements IClienteService.PesquisarService {

  @Autowired
  private IClienteRepository.GetRepository repository;

  @Autowired
  private IMapper mapper;

  @Transactional(readOnly = true)
  @Override
  public Page<ClienteDtoResponse> pesquisar(ClienteFiltro filtro, Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repository.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaClientes -> this.mapper.converterPaginaDeEntidadeParaPaginaDtoResponse(paginaClientes, ClienteDtoResponse.class))
      .orElseThrow();
  }
}

