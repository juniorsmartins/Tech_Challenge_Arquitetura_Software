package com.techchallenge.devnet.application_business_rules.ports.entrada.foto;

import com.techchallenge.devnet.enterprise_business_rules.models.ImagemModel;

public interface FotoProdutoBuscarImagemPorIdServicePort {

  ImagemModel consultarImagemPorId(Long id, String acceptHeader);
}

