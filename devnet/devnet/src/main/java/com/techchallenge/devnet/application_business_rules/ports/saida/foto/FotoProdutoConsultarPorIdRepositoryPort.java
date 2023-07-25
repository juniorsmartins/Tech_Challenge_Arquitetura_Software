package com.techchallenge.devnet.application_business_rules.ports.saida.foto;

import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;

import java.util.Optional;

public interface FotoProdutoConsultarPorIdRepositoryPort {

  Optional<FotoProdutoModel> consultarPorId(Long id);
}

