package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.core.application.use_case.IProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/v1/produtos")
public final class ProdutoDeleteController implements IProdutoController.DeleteController {

  @Autowired
  private IProdutoService.DeletarService deletarService;

  @Override
  public ResponseEntity<?> deletePorId(@PathVariable(name = "id") final Long produtoId) {

    this.deletarService.deletar(produtoId);

    return ResponseEntity
      .noContent()
      .build();
  }
}

