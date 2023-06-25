package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.EmailDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/api/v1/emails")
public final class EmailPostController implements IEmailController.PostController {

  @Autowired
  private IEmailService.EnviarService emailEnviarService;

  @Override
  public ResponseEntity<EmailDtoResponse> enviar(
    @RequestBody @Valid final EmailDtoRequest dtoRequest, final UriComponentsBuilder uriComponentsBuilder) {

    var response = this.emailEnviarService.enviar(dtoRequest);

    return ResponseEntity
      .created(uriComponentsBuilder
        .path("/api/v1/emails/{id}")
        .buildAndExpand(response.getId())
        .toUri())
      .body(response);
  }
}

