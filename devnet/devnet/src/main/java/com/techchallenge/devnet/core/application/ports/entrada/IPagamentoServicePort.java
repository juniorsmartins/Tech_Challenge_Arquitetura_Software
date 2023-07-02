package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PagamentoFiltro;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagamentoServicePort {

  interface PostService {
    PedidoModel iniciarCobrancaDePagamento(PedidoModel pedidoModel);
  }

  interface GetService {
    Page<PagamentoModel> pesquisar(PagamentoFiltro filtro, Pageable paginacao);

    InputStreamResource buscarQrCodePorId(Long id);
  }

  interface PutService {
    PagamentoModel verificarStatusNoGateway(Long idPedido);
  }
}

