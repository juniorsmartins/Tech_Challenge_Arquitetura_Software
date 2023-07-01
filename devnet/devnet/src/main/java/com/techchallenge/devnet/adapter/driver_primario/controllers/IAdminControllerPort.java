package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.IndicadorDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface IAdminControllerPort {

  interface GetController {
    @GetMapping(path = "/indicadores")
    ResponseEntity<IndicadorDtoResponse> buscarIndicadores();
  }
}

