package com.techchallenge.devnet.adapter.driver_primario.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface IAdminControllerPort {

  interface GetController {
    @GetMapping(path = "/indicadores")
    ResponseEntity<Object> buscarIndicadores();
  }
}

