package com.techchallenge.devnet.adapter.driver_primario.controllers.copa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

public interface ICopaControllerPort {

  interface PutController {
    @PutMapping(path = "/status-pronto/{idPedido}")
    ResponseEntity<Object> confirmarPedidoPronto(Long idPedido);

    @PutMapping(path = "/status-finalizado/{idPedido}")
    ResponseEntity<Object> confirmarPedidoFinalizado(Long idPedido);
  }
}

