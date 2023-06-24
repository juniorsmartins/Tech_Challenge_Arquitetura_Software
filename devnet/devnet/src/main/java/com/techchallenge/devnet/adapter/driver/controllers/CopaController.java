package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.ICopaService;
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

