package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {

  interface PostService {
    ClienteDtoResponse cadastrar(ClienteDtoRequest dtoRequest);
  }

  interface PutService {
    ClienteDtoResponse atualizar(Long id, ClienteDtoRequest dtoRequest);
  }

  interface GetService {
    Page<ClienteDtoResponse> pesquisar(ClienteFiltro filtro, Pageable paginacao);
  }

  interface DeleteService {
    void deletar(Long id);
  }
}

