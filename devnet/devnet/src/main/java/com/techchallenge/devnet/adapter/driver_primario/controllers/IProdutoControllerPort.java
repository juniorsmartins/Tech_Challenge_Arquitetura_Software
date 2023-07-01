package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.ProdutoFiltroDto;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ProdutoDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IProdutoControllerPort {

  interface PostController {
    @PostMapping
    ResponseEntity<ProdutoDtoResponse> cadastrar(ProdutoDtoRequest dtoRequest);
  }

  interface PutController {
    @PutMapping(path = "/{id}")
    ResponseEntity<ProdutoDtoResponse> atualizar(Long id, ProdutoDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<ProdutoDtoResponse>> pesquisar(ProdutoFiltroDto filtroDto, Pageable paginacao);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePorId(Long id);
  }
}

