package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.adapter.driver.dtos.request.ClienteDtoRequest;
import com.techchallenge.devnet.core.domain.value_objects.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPedidoService {

  interface CadastrarService {
    PedidoDtoResponse cadastrar(PedidoDtoRequest dtoRequest);
  }

  interface AtualizarService {
    PedidoDtoResponse atualizar(Long id, PedidoDtoRequest dtoRequest);
  }

  interface PesquisarService {
    Page<PedidoDtoResponse> pesquisar(PedidoFiltro filtro, Pageable paginacao);
  }

  interface DeletarService {
    void deletar(Long id);
  }
}

