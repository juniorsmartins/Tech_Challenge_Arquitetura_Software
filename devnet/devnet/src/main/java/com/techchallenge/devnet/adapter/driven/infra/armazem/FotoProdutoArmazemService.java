package com.techchallenge.devnet.adapter.driven.infra.armazem;

import com.techchallenge.devnet.core.application.ports.IFotoProdutoArmazemService;
import com.techchallenge.devnet.core.domain.base.exceptions.ArmazemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public final class FotoProdutoArmazemService implements IFotoProdutoArmazemService {

  @Value("${devnet.armazem.local.diretorio-fotos}")
  private Path caminhoDoDiretorio;

  @Override
  public void armazenar(NovaFoto novaFoto) {

    try {
      Path caminhoDoArquivo = this.pegarCaminhoDoArquivo(novaFoto.getNomeArquivo());
      FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(caminhoDoArquivo));

    } catch (IOException e) {
      throw new ArmazemException("Não foi possível armazenar arquivo de foto.", e);
    }
  }

  private Path pegarCaminhoDoArquivo(String nomeArquivo) {
    return caminhoDoDiretorio.resolve(Path.of(nomeArquivo));
  }
}

