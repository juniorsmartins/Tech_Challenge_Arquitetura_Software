package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.request.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/clientes")
public final class ClientePutController implements IClienteController.PutController {

  @Autowired
  private IClienteService.AtualizarService service;

  @Override
  public ResponseEntity<ClienteDtoResponse> atualizar(@PathVariable(name = "id") final Long clienteId,
                                                      @RequestBody @Valid final ClienteDtoRequest dtoRequest) {

    var response = Optional.of(dtoRequest)
      .map(dto -> this.service.atualizar(clienteId, dto))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

