package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

public interface ICopaControllerPort {

  interface PutController {
    @PutMapping(path = "/status-pronto/{idPedido}")
    ResponseEntity<PedidoDtoResponse> confirmarPedidoPronto(Long idPedido);

    @PutMapping(path = "/status-finalizado/{idPedido}")
    ResponseEntity<PedidoDtoResponse> confirmarPedidoFinalizado(Long idPedido);
  }
}

