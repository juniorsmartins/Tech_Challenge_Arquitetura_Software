package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ProdutoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProdutoService {

  interface CadastrarService {
    ProdutoDtoResponse cadastrar(ProdutoDtoRequest dtoRequest);
  }

  interface AtualizarService {
    ProdutoDtoResponse atualizar(Long id, ProdutoDtoRequest dtoRequest);
  }

  interface PesquisarService {
    Page<ProdutoDtoResponse> pesquisar(ProdutoFiltro filtro, Pageable paginacao);
  }

  interface DeletarService {
    void deletar(Long id);
  }
}

