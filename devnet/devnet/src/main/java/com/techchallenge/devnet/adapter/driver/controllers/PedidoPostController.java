package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IPedidoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/pedidos")
public final class PedidoPostController implements IPedidoController.PostController {

  @Autowired
  private IPedidoService.CadastrarService service;

  @Override
  public ResponseEntity<PedidoDtoResponse> cadastrar(@RequestBody @Valid final PedidoDtoRequest dtoRequest,
                                                     final UriComponentsBuilder uriComponentsBuilder) {
    var response = this.service.cadastrar(dtoRequest);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/api/v1/pedidos/{id}")
        .buildAndExpand(response.getId())
        .toUri())
      .body(response);
  }
}

