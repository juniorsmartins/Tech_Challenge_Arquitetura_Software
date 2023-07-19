package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.FotoProdutoDtoResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IFotoProdutoControllerPort {

  interface PutController {
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FotoProdutoDtoResponse> inserirFotoNoProduto(Long produtoId, FotoProdutoDtoRequest fotoProdutoDtoRequest);
  }

  interface ConsultarPorIdController {
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FotoProdutoDtoResponse> consultarPorId(Long id);
  }

  interface ConsultarImagemPorIdController {
    @GetMapping(path = "/{id}")
    ResponseEntity<InputStreamResource> consultarImagemPorId(Long id, String acceptHeader);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletarPorId(Long id);
  }
}

