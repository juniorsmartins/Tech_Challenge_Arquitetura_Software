package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.Pedido;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPedidoRepository {

  interface PostRepository {
    Pedido salvar(Pedido pedido);
  }

  interface GetRepository {
    Page<Pedido> pesquisar(PedidoFiltro filtro, Pageable paginacao);

    Optional<Pedido> consultarPorId(Long id);

    List<Pedido> consultarPorIdDeCliente(Long idCliente);

    List<Pedido> listar();
  }
}

