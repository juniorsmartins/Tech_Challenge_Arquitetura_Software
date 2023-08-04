package com.techchallenge.devnet.application_business_rules.ports.entrada.foto;

import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoArquivo;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;

import java.io.IOException;

public interface FotoProdutoAtualizarServicePort {

  FotoProdutoModel inserirFotoNoProduto(Long id, FotoProdutoArquivo fotoProdutoArquivo) throws IOException;
}

