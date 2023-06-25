package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IFotoProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/fotos")
public final class FotoProdutoPutController implements IFotoProdutoController.PutController {

  @Autowired
  private IFotoProdutoService.AtualizarService fotoProdutoService;

  @Override
  public ResponseEntity<FotoProdutoDtoResponse> inserirFotoNoProduto(@PathVariable(name = "id") final Long produtoId,
                                                             @Valid final FotoProdutoDtoRequest fotoProdutoDtoRequest) {

    var response = Optional.of(fotoProdutoDtoRequest)
      .map(imagem -> {
        try {
          return this.fotoProdutoService.inserirFotoNoProduto(produtoId, imagem);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      })
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

