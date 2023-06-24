package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PedidoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

public interface ICopaController {

  interface PutController {
    @PutMapping(path = "/status-pronto/{idPedido}")
    ResponseEntity<PedidoDtoResponse> confirmarPedidoPronto(Long idPedido);
  }
}

