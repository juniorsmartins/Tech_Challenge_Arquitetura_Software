package com.techchallenge.devnet.frameworks_and_drivers.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface AdminControllerPort {

  interface GetController {
    @GetMapping(path = "/indicadores")
    ResponseEntity<Object> buscarIndicadores();
  }
}

