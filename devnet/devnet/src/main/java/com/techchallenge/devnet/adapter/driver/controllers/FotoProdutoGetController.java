package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IFotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/fotos")
public class FotoProdutoGetController implements IFotoProdutoController.GetController {

  @Autowired
  private IFotoProdutoService.PesquisarService fotoProdutoPesquisarService;

  @Override
  public ResponseEntity<FotoProdutoDtoResponse> consultarPorId(@PathVariable(name = "id") final Long id) {

    var response = this.fotoProdutoPesquisarService.consultarPorId(id);

    return ResponseEntity
      .ok()
      .body(response);
  }
}

