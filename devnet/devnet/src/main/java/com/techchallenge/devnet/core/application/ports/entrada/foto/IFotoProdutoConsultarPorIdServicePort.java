package com.techchallenge.devnet.core.application.ports.entrada.foto;

import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;

public interface IFotoProdutoConsultarPorIdServicePort {

  FotoProdutoModel consultarPorId(Long id);
}

