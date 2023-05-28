package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/v1/clientes")
public final class ClientePutController implements PoliticaController.PutController {

  @Override
  public ResponseEntity<ClienteDtoResponse> atualizar(@PathVariable(name = "id") UUID clienteId,
                                                      @RequestBody @Valid ClienteDtoRequest dtoRequest) {

    return null;
  }
}

