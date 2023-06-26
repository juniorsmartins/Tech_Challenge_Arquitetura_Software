package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ProdutoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProdutoService {

  interface PostService {
    ProdutoDtoResponse cadastrar(ProdutoDtoRequest dtoRequest);
  }

  interface PutService {
    ProdutoDtoResponse atualizar(Long id, ProdutoDtoRequest dtoRequest);
  }

  interface GetService {
    Page<ProdutoDtoResponse> pesquisar(ProdutoFiltro filtro, Pageable paginacao);
  }

  interface DeleteService {
    void deletar(Long id);
  }
}

