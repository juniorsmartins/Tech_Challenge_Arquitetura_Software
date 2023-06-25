package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.entities.Pagamento;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PagamentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPagamentoRepository {

  interface PostRepository {
    Pagamento salvar(Pagamento pagamento);
  }

  interface GetRepository {
    Page<Pagamento> pesquisar(PagamentoFiltro filtro, Pageable paginacao);

    Optional<Pagamento> consultarPorId(Long id);
  }
}

