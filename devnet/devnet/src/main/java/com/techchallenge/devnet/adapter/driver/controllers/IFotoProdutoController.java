package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.request.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.FotoProdutoDtoResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

public interface IFotoProdutoController {

  interface PutController {
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FotoProdutoDtoResponse> inserirFotoNoProduto(Long produtoId, FotoProdutoDtoRequest fotoProdutoDtoRequest);
  }
}

