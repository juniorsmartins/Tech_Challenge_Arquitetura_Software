package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface IPagamentoServicePort {

  interface PostService {
    PedidoModel iniciarCobrancaDePagamento(PedidoModel pedidoModel);
  }
}

