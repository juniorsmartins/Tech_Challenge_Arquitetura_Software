package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.specification.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface IClienteController {

  interface PostController {
    @PostMapping
    ResponseEntity<ClienteDtoResponse> cadastrar(ClienteDtoRequest dtoRequest, UriComponentsBuilder uriComponentsBuilder);
  }

  interface PutController {
    @PutMapping(path = "/{id}")
    ResponseEntity<ClienteDtoResponse> atualizar(Long id, ClienteDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<ClienteDtoResponse>> pesquisar(ClienteFiltro filtro, Pageable paginacao);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletarPorId(Long id);
  }
}

