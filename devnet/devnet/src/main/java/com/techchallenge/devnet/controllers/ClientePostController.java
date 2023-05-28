package com.techchallenge.devnet.controllers;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.services.PoliticaService;
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
public final class ClientePostController implements PoliticaController.PostController<ClienteDtoRequest, ClienteDtoResponse> {

  @Autowired
  private PoliticaService.CadastrarService<ClienteDtoRequest, ClienteDtoResponse> cadastrarService;

  @Override
  public ResponseEntity<ClienteDtoResponse> cadastrar(@RequestBody @Valid final ClienteDtoRequest dtoRequest,
                                                      final UriComponentsBuilder uriComponentsBuilder) {

    var response = this.cadastrarService.cadastrar(dtoRequest);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/v1/clientes/{id}")
        .buildAndExpand(response)
        .toUri())
      .body(response);
  }
}

