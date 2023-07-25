package com.techchallenge.devnet.application_business_rules.ports.saida.produto;

import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;

import java.util.Optional;

public interface IProdutoConsultarPorIdRepositoryPort {

  Optional<ProdutoModel> consultarPorId(Long id);
}

