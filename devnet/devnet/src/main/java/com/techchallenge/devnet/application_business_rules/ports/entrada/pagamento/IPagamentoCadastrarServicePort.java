package com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface IPagamentoCadastrarServicePort {

  PedidoModel iniciarCobrancaDePagamento(PedidoModel pedidoModel);
}

