package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPedidoRepositoryPort {

  interface PostRepository {
    PedidoEntity salvar(PedidoEntity pedido);
  }

  interface GetRepository {
    Page<PedidoModel> pesquisar(PedidoFiltro filtro, Pageable paginacao);

    Optional<PedidoModel> consultarPorId(Long id);

    List<PedidoModel> consultarPorIdDeCliente(Long idCliente);

    List<PedidoModel> listar();
  }
}

