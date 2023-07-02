package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IFotoProdutoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.ILocalFotoProdutoArmazemService;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import com.techchallenge.devnet.core.domain.models.ImagemModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.util.List;

@Slf4j
@Service
public class FotoProdutoGetService implements IFotoProdutoServicePort.GetService {

  @Autowired
  private IFotoProdutoRepositoryPort.GetRepository fotoProdutoGetRepository;

  @Autowired
  private ILocalFotoProdutoArmazemService localFotoProdutoArmazemService;

  @Transactional(readOnly = true)
  @Override
  public FotoProdutoModel consultarPorId(final Long id) {

    return this.fotoProdutoGetRepository.consultarPorId(id)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }

  @Transactional(readOnly = true)
  @Override
  public ImagemModel consultarImagemPorId(final Long id, final String acceptHeader) {

    return this.fotoProdutoGetRepository.consultarPorId(id)
      .map(fotoProduto -> {

        try {
          this.verificarCompatibilidadeDeTiposDeImagens(fotoProduto, acceptHeader);
        } catch (HttpMediaTypeNotAcceptableException e) {
          throw new RuntimeException(e);
        }

        var imagem = this.localFotoProdutoArmazemService.recuperar(fotoProduto.getNome());

        var imagemModel = ImagemModel.builder()
          .imagem(new InputStreamResource(imagem))
          .mediaTypeFoto(MediaType.parseMediaType(fotoProduto.getTipo()))
          .build();

        return imagemModel;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }

  private void verificarCompatibilidadeDeTiposDeImagens(FotoProdutoModel fotoProduto, String acceptHeader) throws HttpMediaTypeNotAcceptableException {

    MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getTipo());
    List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

    var compativel = mediaTypesAceitas.stream()
      .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

    if (!compativel) {
      log.info(MensagemPadrao.MEDIA_NAO_SUPORTADA);
      throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
    }
  }
}

