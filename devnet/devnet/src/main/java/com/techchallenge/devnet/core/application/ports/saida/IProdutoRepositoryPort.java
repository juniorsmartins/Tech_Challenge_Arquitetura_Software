package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProdutoRepositoryPort {

  interface PostRepository {
    ProdutoModel salvar(ProdutoModel produto);
  }

  interface GetRepository {
    Page<ProdutoModel> pesquisar(ProdutoFiltro filtro, Pageable paginacao);

    Optional<ProdutoModel> consultarPorId(Long id);
  }

  interface DeleteRepository {
    void deletar(ProdutoModel produto);
  }
}

