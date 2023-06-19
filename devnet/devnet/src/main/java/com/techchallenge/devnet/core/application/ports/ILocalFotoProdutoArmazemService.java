package com.techchallenge.devnet.core.application.ports;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public interface ILocalFotoProdutoArmazemService {

  void armazenar(NovaFoto novaFoto);

  default String gerarNomeArquivoParaArmazenar(Long id, String nomeOriginal) {
    return id + "_" + OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "_" + nomeOriginal.toLowerCase().trim();
  }

  @Builder
  @Getter
  class NovaFoto {
    private String nomeArquivo;
    private InputStream inputStream;
  }
}

