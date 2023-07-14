package com.techchallenge.devnet.core.application.ports.entrada.pagamento;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface IPagamentoCadastrarServicePort {

  PedidoModel iniciarCobrancaDePagamento(PedidoModel pedidoModel);
}

