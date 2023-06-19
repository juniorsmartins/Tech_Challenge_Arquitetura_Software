package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.FotoProdutoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/fotos")
public class FotoProdutoGetController implements IFotoProdutoController.GetController {

  @Override
  public ResponseEntity<FotoProdutoDtoResponse> consultarPorId(@PathVariable(name = "id") final Long id) {

    return null;
  }
}

