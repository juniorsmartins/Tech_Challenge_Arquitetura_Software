package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface AdminControllerPort {

  interface GetController {
    @GetMapping(path = "/indicadores")
    ResponseEntity<Object> buscarIndicadores();
  }
}

