package com.techchallenge.devnet.application_business_rules.use_case.foto;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.foto.FotoProdutoBuscarImagemPorIdServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.FotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.FotoProdutoArmazemPort;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.ImagemModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.util.List;

@Slf4j
@Service
public class FotoProdutoArmazemServiceImpl implements FotoProdutoBuscarImagemPorIdServicePort {

  private final FotoProdutoConsultarPorIdRepositoryPort fotoProdutoConsultarPorIdRepository;

  private final FotoProdutoArmazemPort armazemFotoProdutoService;

  public FotoProdutoArmazemServiceImpl(FotoProdutoConsultarPorIdRepositoryPort fotoProdutoConsultarPorIdRepository,
                                       FotoProdutoArmazemPort armazemFotoProdutoService) {
    this.fotoProdutoConsultarPorIdRepository = fotoProdutoConsultarPorIdRepository;
    this.armazemFotoProdutoService = armazemFotoProdutoService;
  }

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

