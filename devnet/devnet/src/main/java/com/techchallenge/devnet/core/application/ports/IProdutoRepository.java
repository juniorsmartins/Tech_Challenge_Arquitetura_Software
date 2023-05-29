package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.entities.Produto;
import com.techchallenge.devnet.core.domain.value_objects.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProdutoRepository {

  interface PostRepository {
    Produto salvar(Produto produto);
  }

  interface GetRepository {
    Page<Produto> pesquisar(ProdutoFiltro filtro, Pageable paginacao);

    Optional<Produto> consultarPorId(Long id);
  }

  interface DeleteRepository {
    void deletar(Produto produto);
  }
}

