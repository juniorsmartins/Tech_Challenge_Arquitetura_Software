package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.core.application.use_case.IClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/v1/clientes")
public final class ClienteDeleteController implements IClienteController.DeleteController {

  @Autowired
  private IClienteService.DeletarService deletarService;

  @Override
  public ResponseEntity<Object> deletarPorId(@PathVariable(name = "id") final Long clienteId) {

    this.deletarService.deletar(clienteId);

    return ResponseEntity
      .noContent()
      .build();
  }
}

