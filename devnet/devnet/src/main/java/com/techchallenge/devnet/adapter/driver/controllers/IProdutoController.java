package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface IProdutoController {

  interface PostController {
    @PostMapping
    ResponseEntity<ProdutoDtoResponse> cadastrar(ProdutoDtoRequest dtoRequest, UriComponentsBuilder uri);
  }
}

