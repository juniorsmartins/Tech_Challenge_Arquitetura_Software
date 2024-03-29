package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.foto;

import com.techchallenge.devnet.application_business_rules.ports.saida.foto.FotoProdutoArmazemPort;
import com.techchallenge.devnet.application_business_rules.exceptions.ArmazemException;
import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FotoProdutoArmazemImpl implements FotoProdutoArmazemPort {

//  private Path caminhoDoDiretorio = Paths.get("C:\\Users\\junio\\OneDrive\\Documentos\\diretorio-devnet");
  private Path caminhoDoDiretorio = Paths.get("/app/armazem");

  @Override
  public void armazenar(NovaFoto novaFoto) {

    try {
      Path caminhoDoArquivo = this.pegarCaminhoDoArquivo(novaFoto.getNomeArquivo());
      FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(caminhoDoArquivo));

    } catch (Exception e) {
      throw new ArmazemException(MensagemPadrao.FOTO_PRODUTO_NAO_ARMAZENADO, e);
    }
  }

  @Override
  public void remover(String nomeArquivo) {

    try {
      Path caminhoDoArquivo = this.pegarCaminhoDoArquivo(nomeArquivo);
      Files.deleteIfExists(caminhoDoArquivo);

    } catch (Exception e) {
      throw new ArmazemException(MensagemPadrao.FOTO_PRODUTO_NAO_APAGADO_DO_ARMAZENAMENTO, e);
    }
  }

  @Override
  public InputStream recuperar(String nomeArquivo) {

    try {
      Path arquivoPath = this.pegarCaminhoDoArquivo(nomeArquivo);
      return Files.newInputStream(arquivoPath);

    } catch (Exception e) {
      throw new ArmazemException(MensagemPadrao.FOTO_PRODUTO_NAO_RECUPERADO_DO_ARMAZENAMENTO, e);
    }
  }

  private Path pegarCaminhoDoArquivo(String nomeArquivo) {
    return this.caminhoDoDiretorio.resolve(Path.of(nomeArquivo));
  }
}

