package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPedidoServicePort {

  interface PostService {
    PedidoModel cadastrar(PedidoModel pedidoModel);
  }

  interface PutService {
    PedidoModel atualizar(Long id, PedidoModel pedidoModel);
  }

  interface GetService {
    Page<PedidoModel> pesquisar(PedidoFiltro filtro, Pageable paginacao);

    List<PedidoModel> listarOrdenadoPorStatusAndDataHoraCadastro();
  }

  interface DeleteService {
    void cancelarPorId(Long id);
  }
}

