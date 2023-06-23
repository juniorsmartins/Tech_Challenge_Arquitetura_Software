package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.domain.entities.Pedido;

public interface IPagamentoService {

  interface PostService {
    Pedido iniciarCobrancaDePagamento(Pedido pedido);
  }
}

