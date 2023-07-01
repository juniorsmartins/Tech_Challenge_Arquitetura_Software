package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProdutoServicePort {

  interface PostService {
    ProdutoModel cadastrar(ProdutoModel produtoModel);
  }

  interface PutService {
    ProdutoModel atualizar(Long id, ProdutoModel produtoModel);
  }

  interface GetService {
    Page<ProdutoModel> pesquisar(ProdutoFiltro filtro, Pageable paginacao);
  }

  interface DeleteService {
    void deletar(Long id);
  }
}

