package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PagamentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPagamentoRepositoryPort {

  interface PostRepository {
    PagamentoModel salvar(PagamentoModel pagamentoModel);
  }

  interface GetRepository {
    Page<PagamentoModel> pesquisar(PagamentoFiltro filtro, Pageable paginacao);

    Optional<PagamentoModel> consultarPorId(Long id);
  }
}

