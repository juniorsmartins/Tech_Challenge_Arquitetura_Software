package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IFotoProdutoService;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.FotoProdutoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/fotos")
public final class FotoProdutoGetController implements IFotoProdutoController.GetController {

  @Autowired
  private IFotoProdutoService.PesquisarService fotoProdutoPesquisarService;

  @Override
  public ResponseEntity<FotoProdutoDtoResponse> consultarPorId(@PathVariable(name = "id") final Long id) {

    var response = this.fotoProdutoPesquisarService.consultarPorId(id);

    return ResponseEntity
      .ok()
      .body(response);
  }

  @Override
  public ResponseEntity<InputStreamResource> consultarImagemPorId(@PathVariable(name = "id") final Long id,
                                                                  @RequestHeader(name = "accept") final String acceptHeader) {
    try {
      var imagemDto = this.fotoProdutoPesquisarService.consultarImagemPorId(id, acceptHeader);

      return ResponseEntity
        .ok()
        .contentType(imagemDto.getMediaTypeFoto())
        .body(imagemDto.getImagem());

    } catch (FotoProdutoNaoEncontradoException fotoProdutoNaoEncontradoException) {

      return ResponseEntity
        .notFound()
        .build();
    }
  }
}

