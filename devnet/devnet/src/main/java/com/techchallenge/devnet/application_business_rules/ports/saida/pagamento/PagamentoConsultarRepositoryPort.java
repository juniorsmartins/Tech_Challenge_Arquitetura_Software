package com.techchallenge.devnet.application_business_rules.ports.saida.pagamento;

import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;

import java.util.Optional;

public interface PagamentoConsultarRepositoryPort {

  Optional<PagamentoModel> consultarPorId(Long id);
}

