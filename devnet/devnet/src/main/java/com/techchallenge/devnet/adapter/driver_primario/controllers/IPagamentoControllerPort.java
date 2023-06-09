package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.PagamentoFiltroDto;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PagamentoDtoResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IPagamentoControllerPort {

  interface PutController {
    @PutMapping(path = "/status/{idPedido}")
    ResponseEntity<PagamentoDtoResponse> verificarStatusNoGateway(Long idPedido);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<PagamentoDtoResponse>> pesquisar(PagamentoFiltroDto pagamentoFiltroDto, Pageable paginacao);

    @GetMapping(path = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<InputStreamResource> buscarQrCodePorId(Long id);
  }
}

