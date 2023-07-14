package com.techchallenge.devnet.core.application.ports.saida.pagamento;

import com.techchallenge.devnet.core.domain.models.PagamentoModel;

import java.util.Optional;

public interface IPagamentoConsultarRepositoryPort {

  Optional<PagamentoModel> consultarPorId(Long id);
}

