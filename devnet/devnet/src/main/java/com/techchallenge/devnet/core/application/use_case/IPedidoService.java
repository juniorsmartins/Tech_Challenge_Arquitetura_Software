package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PedidoFiltro;
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
    void cancelarPorId(Long id);
  }
}

