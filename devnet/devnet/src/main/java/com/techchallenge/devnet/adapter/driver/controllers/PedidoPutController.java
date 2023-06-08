package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IPedidoService;
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
@RequestMapping(path = "/api/v1/pedidos")
public final class PedidoPutController implements IPedidoController.PutController {

  @Autowired
  private IPedidoService.AtualizarService service;

  @Override
  public ResponseEntity<PedidoDtoResponse> atualizar(@PathVariable(name = "id") final Long pedidoId,
                                                     @RequestBody @Valid final PedidoDtoRequest dtoRequest) {

    var response = Optional.of(dtoRequest)
      .map(dto -> this.service.atualizar(pedidoId, dto))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

