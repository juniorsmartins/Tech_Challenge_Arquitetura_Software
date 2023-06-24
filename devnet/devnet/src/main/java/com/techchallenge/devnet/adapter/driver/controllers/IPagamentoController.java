package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.domain.value_objects.specification.PagamentoFiltro;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IPagamentoController {

  interface GetController {
    @GetMapping
    ResponseEntity<Page<PagamentoDtoResponse>> pesquisar(PagamentoFiltro filtro, Pageable paginacao);

    @GetMapping(path = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<InputStreamResource> buscarQrCodePorId(Long id);
  }

  interface PutController {
    @PutMapping(path = "/status/{idPedido}")
    ResponseEntity<PagamentoDtoResponse> confirmarPagamento(Long idPedido);
  }
}

