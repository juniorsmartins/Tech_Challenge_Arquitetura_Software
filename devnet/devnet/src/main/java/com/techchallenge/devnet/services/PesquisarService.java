package com.techchallenge.devnet.services;

import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.filters.ClienteFiltro;
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
