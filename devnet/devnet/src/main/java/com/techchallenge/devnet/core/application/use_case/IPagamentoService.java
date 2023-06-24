package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoFiltro;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagamentoService {

  interface PostService {
    Pedido iniciarCobrancaDePagamento(Pedido pedido);
  }

  interface PesquisarService {
    Page<PagamentoDtoResponse> pesquisar(PagamentoFiltro filtro, Pageable paginacao);

    InputStreamResource consultarQrCodePorId(Long id);
  }
}

