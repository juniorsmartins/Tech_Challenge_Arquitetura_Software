package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.entities.Pagamento;

public interface IPagamentoRepository {

  interface PostRepository {
    Pagamento salvar(Pagamento pagamento);
  }
}

