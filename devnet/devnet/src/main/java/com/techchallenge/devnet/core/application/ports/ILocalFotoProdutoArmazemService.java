package com.techchallenge.devnet.core.application.ports;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public interface ILocalFotoProdutoArmazemService {

  void armazenar(NovaFoto novaFoto);

  void remover(String nomeArquivo);

  default void substituir(String nomeFotoExistente, NovaFoto novaFoto) {
    if (ObjectUtils.isNotEmpty(nomeFotoExistente)) {
      this.remover(nomeFotoExistente);
    }
    this.armazenar(novaFoto);
  }

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

