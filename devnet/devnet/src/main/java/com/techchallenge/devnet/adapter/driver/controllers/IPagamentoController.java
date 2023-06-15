package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.request.PagamentoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PagamentoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface IPagamentoController {

  interface PostController {
    @PostMapping
    ResponseEntity<PagamentoDtoResponse> cadastrar(PagamentoDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<PagamentoDtoResponse>> pesquisar(PagamentoFiltro filtro, Pageable paginacao);
  }
}

