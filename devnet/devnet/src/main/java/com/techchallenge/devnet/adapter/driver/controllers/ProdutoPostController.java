package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/v1/produtos")
public final class ProdutoPostController implements IProdutoController.PostController {

  @Autowired
  private IProdutoService.CadastrarService service;

  @Override
  public ResponseEntity<ProdutoDtoResponse> cadastrar(@RequestBody @Valid final ProdutoDtoRequest dtoRequest,
                                                      final UriComponentsBuilder uriComponentsBuilder) {
    var response = this.service.cadastrar(dtoRequest);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/v1/produtos/{id}")
        .buildAndExpand(response.id())
        .toUri())
      .body(response);
  }
}

