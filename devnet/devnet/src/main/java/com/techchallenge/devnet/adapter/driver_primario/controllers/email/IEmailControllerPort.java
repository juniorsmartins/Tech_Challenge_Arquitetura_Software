package com.techchallenge.devnet.adapter.driver_primario.controllers.email;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.EmailDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface IEmailControllerPort {

  interface PostController {
    @PostMapping
    ResponseEntity<Object> enviar(EmailDtoRequest dtoRequest, UriComponentsBuilder uriComponentsBuilder);
  }
}

