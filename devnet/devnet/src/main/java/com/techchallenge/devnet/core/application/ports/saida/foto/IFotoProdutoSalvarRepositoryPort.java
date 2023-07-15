package com.techchallenge.devnet.core.application.ports.saida.foto;

import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;

import java.io.InputStream;

public interface IFotoProdutoSalvarRepositoryPort {

  FotoProdutoModel salvar(FotoProdutoModel fotoProdutoModel, InputStream dadosArquivo);

  void flush();
}

