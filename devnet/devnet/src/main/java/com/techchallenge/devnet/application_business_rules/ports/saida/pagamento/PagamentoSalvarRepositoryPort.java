package com.techchallenge.devnet.application_business_rules.ports.saida.pagamento;

import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;

public interface PagamentoSalvarRepositoryPort {

  PagamentoModel salvar(PagamentoModel pagamentoModel);
}

