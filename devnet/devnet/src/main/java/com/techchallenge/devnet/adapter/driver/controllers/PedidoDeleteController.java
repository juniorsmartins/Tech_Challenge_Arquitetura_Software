package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.core.application.use_case.IPedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/pedidos")
public final class PedidoDeleteController implements IPedidoController.DeleteController {

  @Autowired
  private IPedidoService.DeletarService deletarService;

  @Override
  public ResponseEntity<Object> deletarPorId(@PathVariable(name = "id") final Long pedidoId) {

    this.deletarService.deletar(pedidoId);

    return ResponseEntity
      .noContent()
      .build();
  }
}

