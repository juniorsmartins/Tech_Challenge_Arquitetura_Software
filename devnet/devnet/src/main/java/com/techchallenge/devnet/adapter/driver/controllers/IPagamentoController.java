package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface IPagamentoController {

  interface PostController {

  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<PagamentoDtoResponse>> pesquisar(PagamentoFiltro filtro, Pageable paginacao);
  }
}

