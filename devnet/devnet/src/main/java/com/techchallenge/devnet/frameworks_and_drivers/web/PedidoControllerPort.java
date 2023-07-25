package com.techchallenge.devnet.frameworks_and_drivers.web;

import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.PedidoFiltroDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface PedidoControllerPort {

  interface PostController {
    @PostMapping
    ResponseEntity<Object> cadastrar(PedidoDtoRequest dtoRequest, UriComponentsBuilder uriComponentsBuilder);
  }

  interface PutController {
    @PutMapping(path = "/{id}")
    ResponseEntity<Object> atualizar(Long id, PedidoDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Object> pesquisar(PedidoFiltroDto pedidoFiltroDto, Pageable paginacao);

    @GetMapping(path = "/ordenado")
    ResponseEntity<Object> listarOrdenadoPorStatusAndDataHoraCadastro();
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> cancelarPorId(Long id);
  }
}

