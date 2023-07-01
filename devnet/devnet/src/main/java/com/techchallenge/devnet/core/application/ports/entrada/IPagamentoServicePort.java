package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PagamentoFiltro;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagamentoServicePort {

  interface PostService {
    PedidoEntity iniciarCobrancaDePagamento(PedidoEntity pedido);
  }

  interface GetService {
    Page<PagamentoDtoResponse> pesquisar(PagamentoFiltro filtro, Pageable paginacao);

    InputStreamResource buscarQrCodePorId(Long id);
  }

  interface PutService {
    PagamentoDtoResponse confirmarPagamento(Long idPedido);
  }
}

