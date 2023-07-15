package com.techchallenge.devnet.core.application.ports.saida.foto;

import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;

import java.util.Optional;

public interface IFotoProdutoConsultarPorIdRepositoryPort {

  Optional<FotoProdutoModel> consultarPorId(Long id);
}

