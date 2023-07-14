package com.techchallenge.devnet.core.application.ports.saida.pagamento;

import com.techchallenge.devnet.core.domain.models.PagamentoModel;

public interface IPagamentoSalvarRepositoryPort {

  PagamentoModel salvar(PagamentoModel pagamentoModel);
}

