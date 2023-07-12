package com.techchallenge.devnet.core.application.ports.saida.produto;

import com.techchallenge.devnet.core.domain.models.ProdutoModel;

import java.util.Optional;

public interface IProdutoConsultarPorIdRepositoryPort {

  Optional<ProdutoModel> consultarPorId(Long id);
}

