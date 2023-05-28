package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.adapter.driver.dtos.PoliticaDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.PoliticaDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PoliticaService {

  interface CadastrarService<T extends PoliticaDtoRequest, R extends PoliticaDtoResponse> {
    R cadastrar(T dtoRequest);
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

