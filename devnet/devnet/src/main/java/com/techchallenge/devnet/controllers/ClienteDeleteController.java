package com.techchallenge.devnet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/v1/clientes")
public final class ClienteDeleteController implements PoliticaController.DeleteController {

  @Override
  public ResponseEntity<?> deletarPorId(@PathVariable(name = "id") UUID clienteId) {

    return null;
  }
}

