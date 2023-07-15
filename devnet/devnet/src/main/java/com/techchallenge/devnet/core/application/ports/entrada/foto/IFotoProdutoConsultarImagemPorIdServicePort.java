package com.techchallenge.devnet.core.application.ports.entrada.foto;

import com.techchallenge.devnet.core.domain.models.ImagemModel;

public interface IFotoProdutoConsultarImagemPorIdServicePort {

  ImagemModel consultarImagemPorId(Long id, String acceptHeader);
}

