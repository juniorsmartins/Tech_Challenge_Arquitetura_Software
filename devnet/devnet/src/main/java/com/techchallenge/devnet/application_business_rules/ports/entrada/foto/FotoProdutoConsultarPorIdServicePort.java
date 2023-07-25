package com.techchallenge.devnet.application_business_rules.ports.entrada.foto;

import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;

public interface FotoProdutoConsultarPorIdServicePort {

  FotoProdutoModel consultarPorId(Long id);
}

