package com.techchallenge.devnet.core.application.use_case.foto;

import com.techchallenge.devnet.core.application.ports.entrada.foto.IFotoProdutoBuscarImagemPorIdServicePort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoArmazemPort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
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
public class FotoProdutoArmazemService implements IFotoProdutoBuscarImagemPorIdServicePort {

  @Autowired
  private IFotoProdutoConsultarPorIdRepositoryPort fotoProdutoConsultarPorIdRepository;

  @Autowired
  private IFotoProdutoArmazemPort armazemFotoProdutoService;

  @Transactional(readOnly = true)
  @Override
  public ImagemModel consultarImagemPorId(final Long id, final String acceptHeader) {

    return this.fotoProdutoConsultarPorIdRepository.consultarPorId(id)
      .map(fotoProduto -> {

        try {
          this.verificarCompatibilidadeDeTiposDeImagens(fotoProduto, acceptHeader);
        } catch (HttpMediaTypeNotAcceptableException e) {
          throw new RuntimeException(e);
        }

        var imagem = this.armazemFotoProdutoService.recuperar(fotoProduto.getNome());

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

