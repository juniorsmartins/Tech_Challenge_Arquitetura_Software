package com.techchallenge.devnet.services;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.filters.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PoliticaService {

  interface CadastrarService {
    ClienteDtoResponse cadastrar(ClienteDtoRequest dtoRequest);
  }

  interface AtualizarService {
    ClienteDtoResponse atualizar(UUID id, ClienteDtoRequest dtoRequest);
  }

  interface PesquisarService {
    Page<ClienteDtoResponse> pesquisar(ClienteFiltro filtro, Pageable paginacao);
  }

  interface DeletarService {
    void deletar(UUID id);
  }
}

