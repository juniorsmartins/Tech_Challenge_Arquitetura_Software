package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/pagamentos")
public final class PagamentoPutController implements IPagamentoController.PutController {

  @Autowired
  private IPagamentoService.AtualizarService atualizarService;

  @Override
  public ResponseEntity<PagamentoDtoResponse> confirmarPagamento(@PathVariable(name = "idPedido") final Long idPedido) {

    var response = this.atualizarService.confirmarPagamento(idPedido);

    return ResponseEntity
      .ok()
      .body(response);
  }
}

