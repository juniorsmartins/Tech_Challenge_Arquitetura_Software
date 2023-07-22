package com.techchallenge.devnet.core.application.ports.saida.pedido;

import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPedidoPesquisarRepositoryPort {

  Page<PedidoModel> pesquisar(PedidoFiltro filtro, Pageable paginacao);
}

