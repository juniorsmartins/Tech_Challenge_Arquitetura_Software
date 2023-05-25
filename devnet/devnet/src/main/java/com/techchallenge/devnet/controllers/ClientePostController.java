package com.techchallenge.devnet.controllers;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping(path = "/v1/clientes")
public final class ClientePostController implements PoliticaController.PostController {

  @Override
  public ResponseEntity<ClienteDtoResponse> cadastrar(@RequestBody @Valid final ClienteDtoRequest dtoRequest,
                                                      final UriComponentsBuilder uri) {

    return null;
  }
}

