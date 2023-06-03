package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {

  interface CadastrarService {
    ClienteDtoResponse cadastrar(ClienteDtoRequest dtoRequest);
  }

  interface AtualizarService {
    ClienteDtoResponse atualizar(Long id, ClienteDtoRequest dtoRequest);
  }

  interface PesquisarService {
    Page<ClienteDtoResponse> pesquisar(ClienteFiltro filtro, Pageable paginacao);
  }

  interface DeletarService {
    void deletar(Long id);
  }
}

