package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.IndicadoresDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface IAdminController {

  interface GetController {
    @GetMapping(path = "/indicadores")
    ResponseEntity<IndicadoresDtoResponse> buscarIndicadores();
  }
}

