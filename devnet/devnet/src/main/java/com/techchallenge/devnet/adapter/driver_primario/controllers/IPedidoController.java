package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface IPedidoController {

  interface PostController {
    @PostMapping
    ResponseEntity<PedidoDtoResponse> cadastrar(PedidoDtoRequest dtoRequest, UriComponentsBuilder uriComponentsBuilder);
  }

  interface PutController {
    @PutMapping(path = "/{id}")
    ResponseEntity<PedidoDtoResponse> atualizar(Long id, PedidoDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<PedidoDtoResponse>> pesquisar(PedidoFiltro filtro, Pageable paginacao);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> cancelarPorId(Long id);
  }
}
