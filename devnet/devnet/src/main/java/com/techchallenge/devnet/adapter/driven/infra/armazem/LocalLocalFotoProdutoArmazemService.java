package com.techchallenge.devnet.adapter.driven.infra.armazem;

import com.techchallenge.devnet.core.application.ports.ILocalFotoProdutoArmazemService;
import com.techchallenge.devnet.core.domain.base.exceptions.ArmazemException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalLocalFotoProdutoArmazemService implements ILocalFotoProdutoArmazemService {

  private Path caminhoDoDiretorio = Paths.get("C:\\Users\\junio\\OneDrive\\Documentos\\diretorio-fotos");

  @Override
  public void armazenar(NovaFoto novaFoto) {

    try {
      Path caminhoDoArquivo = this.pegarCaminhoDoArquivo(novaFoto.getNomeArquivo());
      FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(caminhoDoArquivo));

    } catch (Exception e) {
      throw new ArmazemException("Não foi possível armazenar arquivo de foto.", e);
    }
  }

  private Path pegarCaminhoDoArquivo(String nomeArquivo) {
    return this.caminhoDoDiretorio.resolve(Path.of(nomeArquivo));
  }
}

