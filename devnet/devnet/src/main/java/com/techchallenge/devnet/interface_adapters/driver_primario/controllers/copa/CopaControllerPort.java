package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.copa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

public interface CopaControllerPort {

  interface PutController {
    @PutMapping(path = "/status-pronto/{idPedido}")
    ResponseEntity<Object> confirmarPedidoPronto(Long idPedido);

    @PutMapping(path = "/status-finalizado/{idPedido}")
    ResponseEntity<Object> confirmarPedidoFinalizado(Long idPedido);
  }
}

