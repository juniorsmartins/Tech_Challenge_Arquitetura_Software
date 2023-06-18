package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ProdutoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.specification.ProdutoFiltro;
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

