package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

public interface IProdutoController {

  interface PostController {
    @PostMapping
    ResponseEntity<ProdutoDtoResponse> cadastrar(ProdutoDtoRequest dtoRequest, UriComponentsBuilder uri);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<ProdutoDtoResponse>> pesquisar(ProdutoFiltro filtro, Pageable paginacao);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deletePorId(Long id);
  }
}

