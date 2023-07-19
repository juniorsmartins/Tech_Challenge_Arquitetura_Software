package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.filtros.PedidoFiltroDto;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface IPedidoControllerPort {

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
    ResponseEntity<Page<PedidoDtoResponse>> pesquisar(PedidoFiltroDto pedidoFiltroDto, Pageable paginacao);

    @GetMapping(path = "/ordenado")
    ResponseEntity<List<PedidoDtoResponse>> listarOrdenadoPorStatusAndDataHoraCadastro();
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> cancelarPorId(Long id);
  }
}

