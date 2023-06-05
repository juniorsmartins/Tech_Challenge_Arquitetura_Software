package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IPedidoService;
import com.techchallenge.devnet.core.domain.value_objects.PedidoFiltro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/v1/pedidos")
public final class PedidoGetController implements IPedidoController.GetController {

  @Autowired
  private IPedidoService.PesquisarService service;

  @Override
  public ResponseEntity<Page<PedidoDtoResponse>> pesquisar(final PedidoFiltro filtro,
    @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

    var response = Optional.of(filtro)
      .map(parametrosDePesquisa -> this.service.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

