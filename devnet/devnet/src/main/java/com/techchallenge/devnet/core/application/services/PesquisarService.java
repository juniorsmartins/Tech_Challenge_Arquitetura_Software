package com.techchallenge.devnet.core.application.services;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.filters.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PesquisarService implements PoliticaService.PesquisarService {

  @Override
  public Page<ClienteDtoResponse> pesquisar(ClienteFiltro filtro, Pageable paginacao) {

    return null;
  }
}

