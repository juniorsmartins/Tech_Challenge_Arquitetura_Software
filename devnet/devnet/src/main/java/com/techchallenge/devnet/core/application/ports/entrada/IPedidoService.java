package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPedidoService {

  interface PostService {
    PedidoDtoResponse cadastrar(PedidoDtoRequest dtoRequest);
  }

  interface PutService {
    PedidoDtoResponse atualizar(Long id, PedidoDtoRequest dtoRequest);
  }

  interface GetService {
    Page<PedidoDtoResponse> pesquisar(PedidoFiltro filtro, Pageable paginacao);
  }

  interface DeleteService {
    void cancelarPorId(Long id);
  }
}

