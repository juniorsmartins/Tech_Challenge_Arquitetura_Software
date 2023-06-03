package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.request.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IProdutoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/v1/produtos")
public final class ProdutoPutController implements IProdutoController.PutController {

  @Autowired
  private IProdutoService.AtualizarService service;

  @Override
  public ResponseEntity<ProdutoDtoResponse> atualizar(@PathVariable(name = "id") final Long produtoId,
                                                      @RequestBody @Valid final ProdutoDtoRequest dtoRequest) {

    var response = Optional.of(dtoRequest)
      .map(dto -> this.service.atualizar(produtoId, dto))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

