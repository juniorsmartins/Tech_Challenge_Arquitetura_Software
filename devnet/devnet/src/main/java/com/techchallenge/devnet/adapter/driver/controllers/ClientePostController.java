package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping(path = "/v1/clientes")
public final class ClientePostController implements IClienteController.PostController {

  @Autowired
  private IClienteService.CadastrarService service;

  @Override
  public ResponseEntity<ClienteDtoResponse> cadastrar(@RequestBody @Valid final ClienteDtoRequest dtoRequest,
                                                      final UriComponentsBuilder uriComponentsBuilder) {
    var response = this.service.cadastrar(dtoRequest);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/v1/clientes/{id}")
        .buildAndExpand(response.id())
        .toUri())
      .body(response);
  }
}

