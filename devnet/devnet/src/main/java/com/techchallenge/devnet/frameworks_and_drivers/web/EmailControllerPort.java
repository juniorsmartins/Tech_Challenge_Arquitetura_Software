package com.techchallenge.devnet.frameworks_and_drivers.web;

import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.EmailDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface EmailControllerPort {

  interface PostController {
    @PostMapping
    ResponseEntity<Object> enviar(EmailDtoRequest dtoRequest, UriComponentsBuilder uriComponentsBuilder);
  }
}

