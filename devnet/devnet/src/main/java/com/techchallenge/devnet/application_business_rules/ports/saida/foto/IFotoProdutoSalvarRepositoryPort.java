package com.techchallenge.devnet.application_business_rules.ports.saida.foto;

import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;

import java.io.InputStream;

public interface IFotoProdutoSalvarRepositoryPort {

  FotoProdutoModel salvar(FotoProdutoModel fotoProdutoModel, InputStream dadosArquivo);

  void flush();
}

