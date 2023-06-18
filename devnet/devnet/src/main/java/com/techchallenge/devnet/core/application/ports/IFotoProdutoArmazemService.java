package com.techchallenge.devnet.core.application.ports;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface IFotoProdutoArmazemService {

  void armazenar(NovaFoto novaFoto);

  @Builder
  @Getter
  class NovaFoto {
    private String nomeArquivo;
    private InputStream inputStream;
  }
}

