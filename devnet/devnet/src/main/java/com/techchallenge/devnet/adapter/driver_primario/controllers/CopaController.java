package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.ICopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/copa")
public final class CopaController implements ICopaController.PutController {

  @Autowired
  private ICopaService.AtualizarService copaAtualizarService;

  @Override
  public ResponseEntity<PedidoDtoResponse> confirmarPedidoPronto(@PathVariable(name = "idPedido") final Long idPedido) {

    var response = this.copaAtualizarService.confirmarPedidoPronto(idPedido);

    return ResponseEntity
      .ok()
      .body(response);
  }
}

