package com.techchallenge.devnet.core.application.ports.entrada.foto;

import com.techchallenge.devnet.core.domain.models.ImagemModel;

public interface IFotoProdutoBuscarImagemPorIdServicePort {

  ImagemModel consultarImagemPorId(Long id, String acceptHeader);
}

