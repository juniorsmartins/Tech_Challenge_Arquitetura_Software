package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.value_objects.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPedidoRepository {

  interface PostRepository {
    Pedido salvar(Pedido pedido);
  }

  interface GetRepository {
    Page<Pedido> pesquisar(PedidoFiltro filtro, Pageable paginacao);

    Optional<Pedido> consultarPorId(Long id);
  }

  interface DeleteRepository {
    void deletar(Pedido pedido);
  }
}

