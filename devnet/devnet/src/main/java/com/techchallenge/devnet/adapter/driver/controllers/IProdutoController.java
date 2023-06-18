package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.resposta.ProdutoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.specification.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface IProdutoController {

  interface PostController {
    @PostMapping
    ResponseEntity<ProdutoDtoResponse> cadastrar(ProdutoDtoRequest dtoRequest, UriComponentsBuilder uriComponentsBuilder);
  }

  interface PutController {
    @PutMapping(path = "/{id}")
    ResponseEntity<ProdutoDtoResponse> atualizar(Long id, ProdutoDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<ProdutoDtoResponse>> pesquisar(ProdutoFiltro filtro, Pageable paginacao);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePorId(Long id);
  }
}

