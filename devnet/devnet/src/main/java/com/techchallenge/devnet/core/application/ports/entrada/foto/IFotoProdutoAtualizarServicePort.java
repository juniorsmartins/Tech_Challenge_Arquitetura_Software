package com.techchallenge.devnet.core.application.ports.entrada.foto;

import com.techchallenge.devnet.core.domain.models.FotoProdutoArquivo;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;

import java.io.IOException;

public interface IFotoProdutoAtualizarServicePort {

  FotoProdutoModel inserirFotoNoProduto(Long id, FotoProdutoArquivo fotoProdutoArquivo) throws IOException;
}

