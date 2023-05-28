package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.mappers.ClienteMapper;
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
  private ClienteMapper mapper;

  @Transactional(readOnly = true)
  @Override
  public Page<ClienteDtoResponse> pesquisar(ClienteFiltro filtro, Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repository.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaClientes -> this.mapper.converterPaginaDeEntidadesParaPaginaDeDtosResponse(paginaClientes))
      .orElseThrow();
  }
}

