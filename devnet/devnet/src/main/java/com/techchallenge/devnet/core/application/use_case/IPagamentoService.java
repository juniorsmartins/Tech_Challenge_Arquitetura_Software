package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.value_objects.CobrancaPagamentoDto;

public interface IPagamentoService {

  interface PagamentoPostService {
    CobrancaPagamentoDto iniciarCobrancaDePagamento(Pedido pedido);
  }
}

